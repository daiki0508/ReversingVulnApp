#include <jni.h>
#include <cstring>
#include <random>
#include <jni.h>
#include <string>
#include <string.h>
using namespace std;
//
// Created by oocha on 2021/11/10.
//

int genRand(int min, int max)
{
    static int flag;
    if (flag == 0) {
        random_device rd;
        mt19937 mt(rd());
        uniform_int_distribution<> rand(min,  max);
        rand(mt);
        flag = 1;
    }
    int ret = min + (int)(rand()*(max - min + 1.0)/(1.0+RAND_MAX));
    return ret;
}

char getRandomCharLower(void)
{
//　英小文字の例
    const char CHARS[] = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int index = genRand(0, (strlen(CHARS) - 1));
    char c = CHARS[index];
    return c;
}

char* getRandomCharsLower(int length)
{
    char chars[length+1];
    for(int i=0; i<length; i++){
        chars[i]  = getRandomCharLower();
    }
    return chars;
}

string genRandomStringLower(int length)
{
    std::string text(length, '.');
    generate_n(text.begin(), length,  getRandomCharLower );
    return text;
}

extern "C" jstring
Java_com_websarva_wings_android_vulnmemory_1aes_viewmodel_aesnative_AESNativeViewModel_getAESData(
        JNIEnv *env, jobject thiz) {
    // TODO: implement getAESData()
    return env->NewStringUTF(genRandomStringLower(16).c_str());
}