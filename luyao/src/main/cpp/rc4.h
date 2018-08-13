//
// Created by sun on 2018/8/13.
//

#ifndef ANDROIDUTILS_RC4_H
#define ANDROIDUTILS_RC4_H

class RC4 {
public:
    void ksa(unsigned char *key, int keyLength);

    void *prga(void *in, int len);

    void *doRC4(void *in, unsigned char *key, int dataLen, int keyLen);

private:
    unsigned char S[256];
};

#endif //ANDROIDUTILS_RC4_H
