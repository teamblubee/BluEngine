LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := BLU
LOCAL_SRC_FILES := bufferUtils.cpp glUtils.c
LOCAL_LDLIBS    := -lGLESv2

include $(BUILD_SHARED_LIBRARY)
