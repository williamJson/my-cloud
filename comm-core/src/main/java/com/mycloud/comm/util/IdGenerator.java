package com.mycloud.comm.util;

/**
 * id发号器
 * 参考Twitter snowflake 算法
 *
 * @author wyq
 * <p>
 * 生成规则
 * 64位生成规则【首位是符号位，代表正负，实际有效位数为63位】
 * id 转换成10进制长度为19位
 * <p>
 * 41位时间戳|10位机器号|12位计数器
 * 41位时间戳：
 * 注意：这里不能直接使用时间戳，因为直接使用的话只能到2039-09-07 23:47:35
 * 所以我们采用（当前时间戳-初始时间戳） 这样最多可以使用69年【(1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69】
 * 10位机器码：最多可以供1023台机器使用【也可以使用业务编号+机器号：比如 5为业务编号+5位机器码，根据具体业务自行调整】
 * 12位计数器：每毫秒最多生成4095条数据
 * 这里可以根据项目中实际情况，调整每个位置的长度，比如分布式集群机器数量比较少，可以缩减机器的位数，增加计数器位数
 * 使用注意事项：1、必须关闭时钟同步(linux机器时钟回挑)；2、currentTimeMillis一经定义，不可修改；3、并发量太高的时候，超过了4095，未做处理；4、最大不超过64位
 */
public class IdGenerator {

    /**
     * 初始时间戳：2018-8-4 10:23:0
     * 一经定义，不可修改
     * 可以使用到我退休
     */
    private static final long initTimeMillis = 1533349363289L;
    /**
     * 机器码或进程。
     * 这里也可以手动指定每台实例的ID号；或者通过ZK的临时递增节点自动获取。
     * 固定值
     */
    private static final int pid = 1;

    /**
     * 计数器
     * 需要保证线程安全
     */
    private static volatile long counter;

    /**
     * 当前时间
     */
    private static volatile long currentTimeMillis = System.currentTimeMillis() - initTimeMillis;

    /**
     * 上一次达到4095计数阈值时间
     */
    private static volatile long lastTimeMillis = currentTimeMillis;

    /**
     * 有锁模式
     *
     * @return id
     */
    public static synchronized long next() {
        long series = counter++;
        //单位毫秒内计时器满了，需要重新计时
        if (series >= (1 << 12) - 1) {
            //等待到下一秒
            while (lastTimeMillis == currentTimeMillis) {
                currentTimeMillis = System.currentTimeMillis() - initTimeMillis;
            }
            lastTimeMillis = currentTimeMillis;
            counter = 0;
            series = counter++;
        }
        return (currentTimeMillis << 22) | (pid << 12) | series;
    }
}
