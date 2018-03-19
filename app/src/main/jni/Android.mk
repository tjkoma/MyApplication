LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := JNI_test
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_SRC_FILES := \
	D:\Android_WorkSpace\WaveProgressBar\app\src\main\jni\Android.mk \
	D:\Android_WorkSpace\WaveProgressBar\app\src\main\jni\com_jshc_waveprogressbar_utils_JNIUtil.cpp \

LOCAL_C_INCLUDES += D:\Android_WorkSpace\WaveProgressBar\app\src\debug\jni
LOCAL_C_INCLUDES += D:\Android_WorkSpace\WaveProgressBar\app\src\main\jni

include $(BUILD_SHARED_LIBRARY)
