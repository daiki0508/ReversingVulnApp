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

extern "C"
JNIEXPORT jstring JNICALL
Java_com_websarva_wings_android_reversingvulnapp_viewmodel_aesnative_AESNativeViewModel_getAESData(
        JNIEnv *env, jobject thiz) {
    // TODO: implement getAESData()
    string retValue = "daiki0508";
    retValue += genRandomStringLower(23);
    return env->NewStringUTF(retValue.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_websarva_wings_android_reversingvulnapp_viewmodel_vulnalg_VulnAlgViewModel_getAESData(
        JNIEnv *env, jobject thiz, jint flag) {
    // TODO: implement getAESData()
    string retValue;
    switch (flag) {
        case 0:
            retValue += "RFpJdGICZFR8VAdBdwdebkNnRUVvcEF3bUcFZEBlYlg=";
            break;
        case 1:
            retValue += "Aq8koaJmtHqO7q15EwDAPX2FhGCTZtE0SJkXHCaQ+nM=";
            break;
        default:
            retValue += "Error";
            break;
    }
    return env -> NewStringUTF(retValue.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_websarva_wings_android_reversingvulnapp_viewmodel_vulnalg_VulnAlgViewModelKt_getXorData(
        JNIEnv *env, jclass clazz) {
    // TODO: implement getXorData()
    string retValue = "NjI5ODQ3Nzc3NTM5MjM0NzIzNjQ3NDg1NDIyNDkzODM=";
    return env->NewStringUTF(retValue.c_str());
}