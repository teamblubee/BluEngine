#include "com_blubee_bluengine_jniBuffers_JniBuff.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_freeMemory
(JNIEnv* env, jclass clazz, jobject obj_buffer) {
	char* buffer = (char*)env->GetDirectBufferAddress(obj_buffer);
	free(buffer);
}

JNIEXPORT jobject JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_newDisposableByteBuffer(
		JNIEnv* env, jclass clazz, jint numBytes) {
	char* ptr = (char*) malloc(numBytes);
	return env->NewDirectByteBuffer(ptr, numBytes);
}

JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_clear
(JNIEnv* env, jclass clazz, jobject obj_buffer, jint numBytes) {
	char* buffer = (char*)env->GetDirectBufferAddress(obj_buffer);
	memset(buffer, 0, numBytes);
}

JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_copyJni___3FLjava_nio_Buffer_2II
(JNIEnv* env, jclass clazz, jfloatArray obj_src, jobject obj_dst, jint numFloats, jint offset) {
	unsigned char* dst = (unsigned char*)env->GetDirectBufferAddress(obj_dst);
	float* src = (float*)env->GetPrimitiveArrayCritical(obj_src, 0);
	memcpy(dst, src + offset, numFloats << 2 );
	env->ReleasePrimitiveArrayCritical(obj_src, src, 0);
}

JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_copyJni___3BILjava_nio_Buffer_2II
(JNIEnv* env, jclass clazz, jbyteArray obj_src, jint srcOffset, jobject obj_dst, jint dstOffset, jint numBytes) {
	unsigned char* dst = (unsigned char*)env->GetDirectBufferAddress(obj_dst);
	char* src = (char*)env->GetPrimitiveArrayCritical(obj_src, 0);
	memcpy(dst + dstOffset, src + srcOffset, numBytes);
	env->ReleasePrimitiveArrayCritical(obj_src, src, 0);
}
JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_copyJni___3CILjava_nio_Buffer_2II
(JNIEnv* env, jclass clazz, jcharArray obj_src, jint srcOffset, jobject obj_dst, jint dstOffset, jint numBytes) {
	unsigned char* dst = (unsigned char*)env->GetDirectBufferAddress(obj_dst);
	unsigned short* src = (unsigned short*)env->GetPrimitiveArrayCritical(obj_src, 0);
	memcpy(dst + dstOffset, src + srcOffset, numBytes);
	env->ReleasePrimitiveArrayCritical(obj_src, src, 0);
}
JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_copyJni___3SILjava_nio_Buffer_2II
(JNIEnv* env, jclass clazz, jshortArray obj_src, jint srcOffset, jobject obj_dst, jint dstOffset, jint numBytes) {
	unsigned char* dst = (unsigned char*)env->GetDirectBufferAddress(obj_dst);
	short* src = (short*)env->GetPrimitiveArrayCritical(obj_src, 0);
	memcpy(dst + dstOffset, src + srcOffset, numBytes);
	env->ReleasePrimitiveArrayCritical(obj_src, src, 0);
}
JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_copyJni___3IILjava_nio_Buffer_2II
(JNIEnv* env, jclass clazz, jintArray obj_src, jint srcOffset, jobject obj_dst, jint dstOffset, jint numBytes) {
	unsigned char* dst = (unsigned char*)env->GetDirectBufferAddress(obj_dst);
	int* src = (int*)env->GetPrimitiveArrayCritical(obj_src, 0);
	memcpy(dst + dstOffset, src + srcOffset, numBytes);
	env->ReleasePrimitiveArrayCritical(obj_src, src, 0);
}
JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_copyJni___3JILjava_nio_Buffer_2II
(JNIEnv* env, jclass clazz, jlongArray obj_src, jint srcOffset, jobject obj_dst, jint dstOffset, jint numBytes) {
	unsigned char* dst = (unsigned char*)env->GetDirectBufferAddress(obj_dst);
	long long* src = (long long*)env->GetPrimitiveArrayCritical(obj_src, 0);
	memcpy(dst + dstOffset, src + srcOffset, numBytes);
	env->ReleasePrimitiveArrayCritical(obj_src, src, 0);
}

JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_copyJni___3FILjava_nio_Buffer_2II
(JNIEnv* env, jclass clazz, jfloatArray obj_src, jint srcOffset, jobject obj_dst, jint dstOffset, jint numBytes) {
	unsigned char* dst = (unsigned char*)env->GetDirectBufferAddress(obj_dst);
	float* src = (float*)env->GetPrimitiveArrayCritical(obj_src, 0);
	memcpy(dst + dstOffset, src + srcOffset, numBytes);
	env->ReleasePrimitiveArrayCritical(obj_src, src, 0);
}
JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_copyJni___3DILjava_nio_Buffer_2II
(JNIEnv* env, jclass clazz, jdoubleArray obj_src, jint srcOffset, jobject obj_dst, jint dstOffset, jint numBytes) {
	unsigned char* dst = (unsigned char*)env->GetDirectBufferAddress(obj_dst);
	double* src = (double*)env->GetPrimitiveArrayCritical(obj_src, 0);
	memcpy(dst + dstOffset, src + srcOffset, numBytes);
	env->ReleasePrimitiveArrayCritical(obj_src, src, 0);

}
JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_copyJni__Ljava_nio_Buffer_2ILjava_nio_Buffer_2II
(JNIEnv* env, jclass clazz, jobject obj_src, jint srcOffset, jobject obj_dst, jint dstOffset, jint numBytes) {
	unsigned char* src = (unsigned char*)env->GetDirectBufferAddress(obj_src);
	unsigned char* dst = (unsigned char*)env->GetDirectBufferAddress(obj_dst);
	memcpy(dst + dstOffset, src + srcOffset, numBytes);
}

