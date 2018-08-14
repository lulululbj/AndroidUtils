//
// Created by sun on 2018/8/13.
//

#include <jni.h>
#include <cstring>
#include "rc4.h"

extern "C"
JNIEXPORT jbyteArray JNICALL
Java_luyao_lib_encrypt_RC4Utils_rc4Native(JNIEnv *env, jobject instance, jbyteArray content_,
                                          jbyteArray key_) {
    jbyte *content = env->GetByteArrayElements(content_, NULL);
    jbyte *key = env->GetByteArrayElements(key_, NULL);

    int data_len = env->GetArrayLength(content_);
    unsigned char *chars = new unsigned char[data_len + 1];
    memset(chars, 0, data_len + 1);
    memcpy(chars, content, data_len);
    chars[data_len] = 0;

    int key_len = env->GetArrayLength(key_);
    unsigned char *keys = new unsigned char[key_len + 1];
    memset(keys, 0, key_len + 1);
    memcpy(keys, content, key_len);
    keys[key_len] = 0;

    RC4 rc4;
    rc4.doRC4(chars, keys, data_len, key_len);

    jbyteArray result = env->NewByteArray(data_len);

    env->SetByteArrayRegion(result, 0, data_len, (jbyte*)chars);

    env->ReleaseByteArrayElements(content_, content, 0);
    env->ReleaseByteArrayElements(key_, key, 0);

    return result;
}