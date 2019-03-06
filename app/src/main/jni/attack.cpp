#include <jni.h>
#include <android/log.h>
#include <unistd.h>
#include <cerrno>
#include <cstring>
#include <pthread.h>
#include <sys/syscall.h>

#include <com_herbwen_myjnitest_MainActivity.h>

#include<cstdlib>
#include<cstdio>
#include<sys/types.h>
#include<sys/sysinfo.h>
#include<unistd.h>

#include<sched.h>
#include<cctype>
#define THREAD_MAX_NUM 100  //1个CPU内的最多进程数

int num=0;  //cpu中核数

#define TAG "Attack"
#define DEBUG 1

#ifdef DEBUG
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)
#else
#define LOGD(...) ((void)0)
#define LOGE(...) ((void)0)
#endif

static long getCores() {
    return sysconf(_SC_NPROCESSORS_CONF);
}

JNIEXPORT jlong JNICALL Java_com_herbwen_myjnitest_MainActivity_getCores (JNIEnv *env, jobject object) {
    return getCores();
}

JNIEXPORT void JNICALL Java_com_herbwen_myjnitest_MainActivity_bindToCPU (JNIEnv *env, jobject object) {
    int cpu=0;
    long cores = getCores();
    LOGD("get cpu number = %ld\n",cores);
    if (cpu >= cores) {
        LOGE("your set cpu is beyond the cores,exit...");
        return;
    }

    cpu_set_t mask;
    CPU_ZERO(&mask);
    CPU_SET(cpu,&mask);
    if (sched_setaffinity(0, sizeof(mask), &mask) == -1)
    {
        LOGD("warning: could not set CPU affinity, continuing...\n");
    }else
    {
        LOGD("set affinity to %d success",cpu);
    }
}
