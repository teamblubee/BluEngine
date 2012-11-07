#include "com_blubee_bluengine_jniOpenGL_BLU20.h"
#include <GLES2/gl2.h>
#include <GLES2/gl2ext.h>
#include <android/log.h>


JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniOpenGL_BLU20_glDrawElements
(JNIEnv *jniEnv, jclass classHandle, jint mode, jint count, jint type, jint offset)
{
	 glDrawElements( mode, count, type, (const void*)offset);
}


JNIEXPORT void JNICALL Java_com_blubee_bluengine_jniOpenGL_BLU20_glVertexAttribPointer
(JNIEnv *jniEnv, jclass classHandle, jint index, jint size, jint type, jboolean normalized, jint stride, jint offset)
{
   glVertexAttribPointer(index, size, type, normalized, stride, (const void*)offset);
}
