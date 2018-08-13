//
// Created by sun on 2018/8/13.
//

#include <jni.h>

JNIEXPORT jbyteArray JNICALL
Java_luyao_lib_encrypt_RC4Utils_rc4_native(JNIEnv *env, jobject instance, jbyteArray content_,
                                           jbyteArray key_) {
    jbyte *content = env->GetByteArrayElements(content_, NULL);
    jbyte *key = env->GetByteArrayElements(key_, NULL);

// TODO

//    (*env)->ReleaseByteArrayElements(env, content_, content, 0);
//    (*env)->ReleaseByteArrayElements(env, key_, key, 0);
}