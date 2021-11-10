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
        // デバイスから乱数を取得する
        random_device rd;
        // 乱数のシードを設定する
        mt19937 mt(rd());
        uniform_int_distribution<> rand(min,  max);
        // 乱数を生成する
        rand(mt);
        flag = 1;
    }
    int ret = min + (int)(rand()*(max - min + 1.0)/(1.0+RAND_MAX));
    return ret;
}

char getRandomCharLower()
{
//　文字列の定義
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
    string retValue = "daiki0508";
    retValue += genRandomStringLower(23);
    return env->NewStringUTF(retValue.c_str());
}