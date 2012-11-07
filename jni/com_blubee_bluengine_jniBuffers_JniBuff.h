/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_blubee_bluengine_jniBuffers_JniBuff */

#ifndef _Included_com_blubee_bluengine_jniBuffers_JniBuff
#define _Included_com_blubee_bluengine_jniBuffers_JniBuff
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_blubee_bluengine_jniBuffers_JniBuff
 * Method:    freeMemory
 * Signature: (Ljava/nio/ByteBuffer;)V
 */
JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_freeMemory
  (JNIEnv *, jclass, jobject);

/*
 * Class:     com_blubee_bluengine_jniBuffers_JniBuff
 * Method:    newDisposableByteBuffer
 * Signature: (I)Ljava/nio/ByteBuffer;
 */
JNIEXPORT jobject JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_newDisposableByteBuffer
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_blubee_bluengine_jniBuffers_JniBuff
 * Method:    clear
 * Signature: (Ljava/nio/ByteBuffer;I)V
 */
JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_clear
  (JNIEnv *, jclass, jobject, jint);

/*
 * Class:     com_blubee_bluengine_jniBuffers_JniBuff
 * Method:    copyJni
 * Signature: ([FLjava/nio/Buffer;II)V
 */
JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_copyJni___3FLjava_nio_Buffer_2II
  (JNIEnv *, jclass, jfloatArray, jobject, jint, jint);

/*
 * Class:     com_blubee_bluengine_jniBuffers_JniBuff
 * Method:    copyJni
 * Signature: ([BILjava/nio/Buffer;II)V
 */
JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_copyJni___3BILjava_nio_Buffer_2II
  (JNIEnv *, jclass, jbyteArray, jint, jobject, jint, jint);

/*
 * Class:     com_blubee_bluengine_jniBuffers_JniBuff
 * Method:    copyJni
 * Signature: ([CILjava/nio/Buffer;II)V
 */
JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_copyJni___3CILjava_nio_Buffer_2II
  (JNIEnv *, jclass, jcharArray, jint, jobject, jint, jint);

/*
 * Class:     com_blubee_bluengine_jniBuffers_JniBuff
 * Method:    copyJni
 * Signature: ([SILjava/nio/Buffer;II)V
 */
JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_copyJni___3SILjava_nio_Buffer_2II
  (JNIEnv *, jclass, jshortArray, jint, jobject, jint, jint);

/*
 * Class:     com_blubee_bluengine_jniBuffers_JniBuff
 * Method:    copyJni
 * Signature: ([IILjava/nio/Buffer;II)V
 */
JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_copyJni___3IILjava_nio_Buffer_2II
  (JNIEnv *, jclass, jintArray, jint, jobject, jint, jint);

/*
 * Class:     com_blubee_bluengine_jniBuffers_JniBuff
 * Method:    copyJni
 * Signature: ([JILjava/nio/Buffer;II)V
 */
JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_copyJni___3JILjava_nio_Buffer_2II
  (JNIEnv *, jclass, jlongArray, jint, jobject, jint, jint);

/*
 * Class:     com_blubee_bluengine_jniBuffers_JniBuff
 * Method:    copyJni
 * Signature: ([FILjava/nio/Buffer;II)V
 */
JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_copyJni___3FILjava_nio_Buffer_2II
  (JNIEnv *, jclass, jfloatArray, jint, jobject, jint, jint);

/*
 * Class:     com_blubee_bluengine_jniBuffers_JniBuff
 * Method:    copyJni
 * Signature: ([DILjava/nio/Buffer;II)V
 */
JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_copyJni___3DILjava_nio_Buffer_2II
  (JNIEnv *, jclass, jdoubleArray, jint, jobject, jint, jint);

/*
 * Class:     com_blubee_bluengine_jniBuffers_JniBuff
 * Method:    copyJni
 * Signature: (Ljava/nio/Buffer;ILjava/nio/Buffer;II)V
 */
JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniBuffers_JniBuff_copyJni__Ljava_nio_Buffer_2ILjava_nio_Buffer_2II
  (JNIEnv *, jclass, jobject, jint, jobject, jint, jint);

#ifdef __cplusplus
}
#endif
#endif
