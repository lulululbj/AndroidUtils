//
// Created by sun on 2018/8/13.
//
#include "rc4.h"

void RC4::ksa(unsigned char *key, int keyLength) {
    for (int i = 0; i < 256; ++i) {
        S[i] = i;
    }

    unsigned int j = 0;
    unsigned char temp;

    for (int i = 0; i < 256; ++i) {
        j = (j + S[i] + key[i % keyLength]) % 256;

        temp = S[i];
        S[i] = S[j];
        S[j] = temp;
    }
}

char *RC4::prga(unsigned char *in, int len) {
    char *k = new char[len];
    unsigned char *result = in;
    unsigned int i = 0, j = 0, x = 0;
    unsigned char temp;

    for (int z = 0; z < len; ++z) {
        i = (i + 1) % 256;
        j = (j + S[i]) % 256;

        temp = S[i];
        S[i] = S[j];
        S[j] = temp;

        k[x] = S[(S[i] + S[j]) % 256];

        result[z] = result[z] ^ k[z];
    }

    return (char *) (result);
}

char *RC4::doRC4(unsigned char *in, unsigned char *key, int dataLen, int keyLen) {
    ksa(key, keyLen);
    return prga(in, dataLen);
}
