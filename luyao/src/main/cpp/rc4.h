//
// Created by sun on 2018/8/13.
//

#ifndef ANDROIDUTILS_RC4_H
#define ANDROIDUTILS_RC4_H

class RC4 {
public:
    void ksa(unsigned char *key, int keyLength);

    char *prga(unsigned char *in, int len);

    char *doRC4(unsigned char *in, unsigned char *key, int dataLen, int keyLen);

private:
    unsigned char S[256];
};

#endif //ANDROIDUTILS_RC4_H
