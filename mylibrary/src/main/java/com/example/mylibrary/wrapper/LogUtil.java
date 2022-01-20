package com.example.mylibrary.wrapper;

import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class LogUtil {
    private static final String LOG_TAG = "UILayouts";

    private static final int DOMAIN_ID = 0xD000F00;

    private static final HiLogLabel LOG_LABEL = new HiLogLabel(HiLog.LOG_APP, DOMAIN_ID, LogUtil.LOG_TAG);

    private static final String LOG_FORMAT = "%{public}s: %{public}s";

    private LogUtil() {
        // Do nothing */
    }

    /*
     * Print debug log
     *
     * @param tag log tag
     * @param msg log message
     */
    public static void debug(String tag, String msg) {
        HiLog.debug(LOG_LABEL, LOG_FORMAT, tag, msg);
    }

    /*
     * Print info log
     *
     * @param tag log tag
     * @param msg log message
     */
    public static void info(String tag, String msg) {
        HiLog.info(LOG_LABEL, LOG_FORMAT, tag, msg);
    }

    /*
     * Print warn log
     *
     * @param tag log tag
     * @param msg log message
     */
    public static void warn(String tag, String msg) {
        HiLog.warn(LOG_LABEL, LOG_FORMAT, tag, msg);
    }

    /*
     * Print error log
     *
     * @param tag log tag
     * @param msg log message
     */
    public static void error(String tag, String msg) {
        HiLog.error(LOG_LABEL, LOG_FORMAT, tag, msg);
    }

}