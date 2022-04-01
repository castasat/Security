#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_p_l_e_x_u_s_sdk_NativeLib_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}