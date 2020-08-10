package com.qixi.wfw.idgenerator.service;

import org.springframework.stereotype.Service;

/**
 * id生成器
 * （单例，因为Spring bean默认为单例，所以此处不再自己实现单例）
 *
 * ID结构（共64位）
 * 0 - 0000000000 0000000000 0000000000 0000000000 0000000000 - 0000000000 000
 *
 * 1位符号位，long类型最高位是符号位，0为正数，1为负数，id一般是正数，所以最高位固定为0
 * 50位为时间戳，存储的是时间戳的差值，开始时间戳一般为项目投入使用的时间，50位存储大概可以使用35604年
 * 13为存储序列，毫秒内的自增值，就是说每毫秒最多生成8192个id
 *
 * @author ZhengNC
 * @date 2020/6/18 16:11
 */
@Service
public class QixiIdWorker {

    /** 开始时间戳 (2020-06-01) */
    private final long startTimeMillis = 1590940800000L;

    /** 序列在id中占的位数 */
    private final long sequenceBits = 13L;

    /** 时间戳向左移13位 */
    private final long timestampLeftShift = sequenceBits;

    /** 生成序列的掩码，这里为8191 */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /** 毫秒内序列(0~8191) */
    private long sequence = 0L;

    /** 上次生成ID的时间戳 */
    private long lastTimestamp = -1L;

    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return
     */
    public synchronized long nextId(){
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }else {//时间戳改变，毫秒内序列重置
            sequence = 0L;
        }
        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - startTimeMillis) << timestampLeftShift) | sequence;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }
}
