#include <jni.h>
#include <string>
#include <syslog.h>
#include <cstdio>
#include <iostream>
#include <memory>
#include <stdexcept>
#include <string>
#include <array>
#include <sys/system_properties.h>
#include <mntent.h>
#include <unistd.h>

#pragma clang diagnostic push
#pragma ide diagnostic ignored "OCUnusedGlobalDeclarationInspection"
#pragma clang diagnostic ignored "-Wunknown-pragmas"
#pragma ide diagnostic ignored "UnusedLocalVariable"
#pragma ide diagnostic ignored "UnusedValue"
#pragma ide diagnostic ignored "UnusedParameter"

#define NULLABLE
#define NOTNULL

/**
 * Converts C (char *) string to
 * Java String (jstring)
 */
NULLABLE jstring charStringToJString(
        JNIEnv *env,
        NULLABLE const char *charString
) {
    // TODO try-catch
    return NULLABLE (jstring) env->NewStringUTF(charString);
}

/**
 * Converts ascii or UTF-8 Java String (jstring)
 * to CPP string (std::string)
 */
NOTNULL std::string jstringToStdString(
        JNIEnv *env,
        NULLABLE jstring javaString
) {
    std::string result;
    result = ""; // default value

    // TODO try-catch
    // get null pointer exception
    jclass nullPointerException = env->FindClass("java/lang/NullPointerException");

    if (javaString != nullptr) {
        const auto stringClass = (jclass) env->GetObjectClass(javaString);
        const auto getBytesMethod = (jmethodID) env->GetMethodID(
                stringClass, // class
                "getBytes", // methodName
                "(Ljava/lang/String;)[B" // method signature: charset, byte array
        );
        const auto javaByteArray = (jbyteArray) env->CallObjectMethod(
                javaString, // object
                getBytesMethod, // method
                NULLABLE charStringToJString(env, "UTF-8") // charset
        );
        auto javaByteArrayLength = (size_t) env->GetArrayLength(javaByteArray);
        jbyte *bytesPointer = env->GetByteArrayElements(
                javaByteArray,
                nullptr // nullable jboolean isCopy
        );
        result = std::string(
                (char *) bytesPointer, // cast byte pointer to char pointer
                javaByteArrayLength // byte array length
        );
        // TODO check before clear
        // clear
        env->ReleaseByteArrayElements(javaByteArray, bytesPointer, JNI_ABORT);
        env->DeleteLocalRef(javaByteArray);
        env->DeleteLocalRef(stringClass);
    }

    return result;
}

/**
 * Converts CPP (std::string) to
 * Java String (jstring)
 */
NULLABLE jstring stdStringToJString(
        JNIEnv *env,
        NULLABLE const std::string &stdString
) {
    // TODO try-catch
    NULLABLE jstring result = charStringToJString(env, stdString.c_str());
    // if NULL try to return empty Java String
    if (!result) NULLABLE result = charStringToJString(env, std::string("").c_str());
    // if NULL return nullptr
    if (!result) NULLABLE result = nullptr;
    return result;
}

/**
 * check if BuildConfig.DEBUG is true
 */
NOTNULL bool isBuildConfigDebug(
        JNIEnv *env
) {
    NOTNULL bool result = false;

    // TODO try-catch
    // get null pointer exception
    jclass nullPointerException = env->FindClass("java/lang/NullPointerException");

    // get static class ru.sberbank.sberapps.sdk.BuildConfig
    jclass buildConfigClass =
            env->FindClass("p/l/e/x/u/s/security/BuildConfig");
    if (!buildConfigClass) { env->ThrowNew(nullPointerException, "buildConfigClass is NULL"); }

    // determine BuildConfig.BUILD_TYPE: String
    jfieldID buildTypeStringFieldId =
            env->GetStaticFieldID(buildConfigClass, "BUILD_TYPE", "Ljava/lang/String;");
    auto buildTypeString =
            (jstring) env->GetStaticObjectField(buildConfigClass, buildTypeStringFieldId);
    if (!buildTypeString) { env->ThrowNew(nullPointerException, "buildTypeString is NULL"); }
    const char *buildType = env->GetStringUTFChars(buildTypeString, nullptr);
    //syslog(LOG_DEBUG, "%s", buildType);

    // determine BuildConfig.DEBUG: Boolean
    jfieldID debugBooleanFieldId =
            env->GetStaticFieldID(buildConfigClass, "DEBUG", "Z");
    auto debugBoolean =
            (jboolean) env->GetStaticBooleanField(buildConfigClass, debugBooleanFieldId);

    // convert jboolean to bool
    result = (bool) debugBoolean;

    // clear
    env->DeleteLocalRef(nullPointerException);
    env->DeleteLocalRef(buildConfigClass);
    env->DeleteLocalRef(buildTypeString);

    return result;
}

/**
 * logD(JNIEnv, message) function with c_str() argument (char*)
 */
void logD(
        JNIEnv *env,
        const char *message
) {
    // TODO try-catch
    if (isBuildConfigDebug(env)) {
        // TODO formatted string - potential risk
        syslog(LOG_DEBUG, "%s", message);
    }
}

/**
 * logD(JNIEnv, message) function with std::string argument ("message")
 */
void logD(
        JNIEnv *env,
        const std::string &message
) {
    // TODO try-catch
    const char *cStringMessage;
    cStringMessage = message.c_str();
    logD(env, cStringMessage);
}

/**
 * LogD(JNIEnv, message) function with jstring argument
 */
void JNICALL logD(
        JNIEnv *env,
        jstring message
) {
    // TODO try-catch
    logD(env, jstringToStdString(env, message));
}

/**
 * disableScreenshots JNI function
 */
extern "C"
JNIEXPORT void JNICALL
Java_p_l_e_x_u_s_sdk_screenshots_SdkCallbacks_disableScreenshots(
        JNIEnv *env,
        jobject sdkCallbacksObject,
        jobject activityObject
) {
    // TODO try-catch
    // get null pointer exception
    jclass nullPointerException = env->FindClass("java/lang/NullPointerException");

    // find class Activity
    jclass activityClass = env->FindClass("android/app/Activity");

    // method getWindow
    if (!activityClass) { env->ThrowNew(nullPointerException, "activityClass is NULL"); }
    jmethodID getWindowMethod = env->GetMethodID(
            activityClass,
            "getWindow",
            "()Landroid/view/Window;"
    );

    // windowObject object of Window class
    if (!getWindowMethod) { env->ThrowNew(nullPointerException, "getWindowMethod is NULL"); }
    if (!activityObject) { env->ThrowNew(nullPointerException, "activityObject is NULL"); }
    jobject windowObject = env->CallObjectMethod(activityObject, getWindowMethod);

    // find class Window
    jclass windowClass = env->FindClass("android/view/Window");

    // method setFlagsMethod
    if (!windowClass) { env->ThrowNew(nullPointerException, "windowClass is NULL"); }
    jmethodID setFlagsMethod = env->GetMethodID(
            windowClass,
            "setFlags",
            "(II)V"
    );

    // set flags android.view.WindowManager.LayoutParams.FLAG_SECURE = 8192
    if (!windowObject) { env->ThrowNew(nullPointerException, "windowObject is NULL"); }
    if (!setFlagsMethod) { env->ThrowNew(nullPointerException, "setFlagsMethod is NULL"); }
    env->CallVoidMethod(
            windowObject,
            setFlagsMethod,
            0x2 << 12,
            0x2 << 12
    );

    // clear
    env->DeleteLocalRef(nullPointerException);
    env->DeleteLocalRef(activityClass);
    env->DeleteLocalRef(windowObject);
    env->DeleteLocalRef(windowClass);
    env->DeleteLocalRef(activityObject);
}

/**
 * concatenate C strings (char *)
 */
static char *concatenateCharStrings(
        const char *firstString,
        const char *secondString
) {
    size_t lengthBuffer = lengthBuffer = 1 + // one byte for '\0' string terminator
                                         strlen(firstString) + strlen(secondString);
    char *result = (char *) malloc(lengthBuffer);
    if (result == nullptr) {
        return nullptr; // malloc failed
    }
    memset(
            result,      // destination
            0,
            lengthBuffer // total destination length
    );
    strcpy(
            result,      // destination
            firstString  // copy firstString to result from the beginning
    );
    strcat(
            result,      // destination
            secondString // add secondString to the last filled byte of result
    );
    return result;
}

/**
 * which JNI function
 * @param parameter - a String to execute "which 'parameter'" command
 */
extern "C"
JNIEXPORT jstring JNICALL
Java_p_l_e_x_u_s_sdk_root_which_1su_ExecuteWhichNatively_00024Companion_which(
        JNIEnv *env,
        jobject companionObject,
        jstring parameter
) {
    std::string result;

    char *command = concatenateCharStrings(
            (char *) "which ",
            (char *) jstringToStdString(env, parameter).c_str()
    );

    // TODO don't forget to remove local reference
    std::array<char, BUFSIZ> buffer{}; // 1024 from <stdio.h>

    // fork process and execute shell command
    // open output stream for readonly
    std::shared_ptr<FILE> pipe(popen(command, "r"), pclose);
    if (!pipe) throw std::runtime_error("pipe is NULL");

    while (!feof(pipe.get())) {
        if (fgets(buffer.data(), BUFSIZ, pipe.get()) != nullptr)
            result += buffer.data();
    }

    // logD(env, result);

    // TODO clear

    // convert std::string to Java String
    return (jstring) stdStringToJString(env, result);
}

/**
 * Converts jBoolean to jObject<Boolean> which can be used in kotlin
 * @param env - JVM environment
 * @param value - jBoolean value
 * @return - jObject<Boolean>
 */
jobject jBooleanToBooleanJObject(
        JNIEnv *env,
        jboolean value
) {
    jclass booleanClass = env->FindClass("java/lang/Boolean");
    jmethodID constructorID = env->GetMethodID(
            booleanClass,
            "<init>", // constructor
            "(Z)V" // boolean
    );
    jobject booleanResult = env->NewObject(
            booleanClass,
            constructorID,
            value
    );
    // TODO don't forget to clean up
    return booleanResult;
}

jboolean searchStringIsInSystemProperties(
        JNIEnv *env,
        jstring searchString,
        const char *const propertyString
) {
    jboolean result = false;
    if (searchString != nullptr) {
        std::string searchStdString = jstringToStdString(env, searchString);
        const char *searchCharString = searchStdString.c_str();
        char value[PROP_VALUE_MAX + 1];
        int length = __system_property_get(propertyString, value);
        // A length 0 value indicates that the property is not defined
        if (length > 0 &&
            strlen(value) >= strlen(searchCharString) &&
            strstr(value, searchCharString) != nullptr) {
            result = true;
        } else {
        }
    } else {
        logD(env, "searchString may not be NULL");
        result = false;
    }
    return result;
}

/**
 * inOsBuildKeys(buildKeysJString: String) JNI function
 *
 * Checks if android OS kernel was built with buildKeysJString, like:
 *    "test-keys" (AOSP - custom system build - rooted),
 *    "dev-keys" (emulator build - rooted),
 *    "release-keys" (official build - more secure, but still might be rooted)
 */
extern "C"
JNIEXPORT jobject JNICALL
Java_p_l_e_x_u_s_sdk_root_build_1tags_DiscoverBuildKeysNatively_00024Companion_inOsBuildKeys(
        JNIEnv *env,
        jobject companionObject,
        jstring buildKeysJString
) {
    jboolean result = false;
    // Comma-separated tags describing the build, like= "unsigned,debug".
    const char *const ANDROID_OS_BUILD_TAGS = "ro.build.tags";
    result = searchStringIsInSystemProperties(env,
                                              buildKeysJString,
                                              ANDROID_OS_BUILD_TAGS);
    return jBooleanToBooleanJObject(env, result);
}

/**
 * Get mount entries
 */
struct mntent *getMntent(FILE *fp, struct mntent *entries, char *buf, int buf_len) {
    // TODO try-catch
    while (fgets(buf, buf_len, fp) != nullptr) {
        // Entries look like "/dev/block/vda /system ext4 ro,seclabel,relatime,data=ordered 0 0".
        // That is: mnt_fsname mnt_dir mnt_type mnt_opts mnt_freq mnt_passno.
        int fsname0, fsname1, dir0, dir1, type0, type1, opts0, opts1;
        // TODO replace sscanf() with strtol()
        if (sscanf(buf, " %n%*s%n %n%*s%n %n%*s%n %n%*s%n %d %d",
                   &fsname0, &fsname1, &dir0, &dir1, &type0, &type1, &opts0, &opts1,
                   &entries->mnt_freq, &entries->mnt_passno) == 2) {
            entries->mnt_fsname = &buf[fsname0];
            buf[fsname1] = '\0';
            entries->mnt_dir = &buf[dir0];
            buf[dir1] = '\0';
            entries->mnt_type = &buf[type0];
            buf[type1] = '\0';
            entries->mnt_opts = &buf[opts0];
            buf[opts1] = '\0';
            return entries;
        }
    }
    // TODO clean up
    return nullptr;
}

/**
 * Checks if mount options are present
 * @param pMnt - mount entries
 * @param pOpt - mount options
 * @return - boolean value
 */
bool isPresentMntOpt(const struct mntent *pMnt, const char *pOpt) {
    // TODO try-catch
    char *token = pMnt->mnt_opts;
    const char *end = pMnt->mnt_opts + strlen(pMnt->mnt_opts);
    const size_t optLen = strlen(pOpt);
    while (token != nullptr) {
        const char *tokenEnd = token + optLen;
        if (tokenEnd > end) break;
        if (memcmp(token, pOpt, optLen) == 0 &&
            (*tokenEnd == '\0' || *tokenEnd == ',' || *tokenEnd == '=')) {
            return true;
        }
        token = strchr(token, ',');
        if (token != nullptr) {
            token++;
        }
    }
    // TODO clean up
    return false;
}

/**
 * checkWriteAccess() JNI function
 * checks if system paths have write access permissions
 */
extern "C"
JNIEXPORT jobject JNICALL
Java_p_l_e_x_u_s_sdk_root_write_1permissions_WriteOnSystem_00024Companion_checkWriteAccess(
        JNIEnv *env,
        jobject companionObject
) {
    jboolean result = false;
    const char *const MG_READ_ONLY_PATH[] = {
            "/system",
            "/system/bin",
            "/system/sbin",
            "/system/xbin",
            "/vendor/bin",
            "/sbin",
            "/etc",
            0 // NOLINT(modernize-use-nullptr)
    };
    // TODO try-catch
    FILE *file = fopen("/proc/mounts", "r");
    char mntent_strings[BUFSIZ];
    if (file != nullptr) {
        struct mntent entries = {0}; // NOLINT(modernize-use-nullptr)
        while (getMntent(file, &entries, mntent_strings, sizeof(mntent_strings)) != nullptr) {
            for (size_t i = 0; MG_READ_ONLY_PATH[i]; i++) {
                if (strcmp((&entries)->mnt_dir, MG_READ_ONLY_PATH[i]) == 0 &&
                    isPresentMntOpt(&entries, "rw")) {
                    // start logging
                    logD(env, (&entries)->mnt_fsname);
                    logD(env, (&entries)->mnt_dir);
                    logD(env, (&entries)->mnt_opts);
                    logD(env, (&entries)->mnt_type);
                    // end logging
                    result = true;
                    break;
                }
            }
            memset(&entries, 0, sizeof(entries));
        }
        fclose(file);
    } else {
        logD(env, "proc/mounts should not be NULL");
        result = false;
    }
    // TODO clean up
    return jBooleanToBooleanJObject(env, result);
}

/**
 * checkDebuggable() JNI function
 * checks system properties to find if OS is debuggable
 */
extern "C"
JNIEXPORT jobject JNICALL
Java_p_l_e_x_u_s_sdk_root_os_1debuggable_CheckOsDebuggable_00024Companion_checkDebuggable(
        JNIEnv *env,
        jobject companionObject
) {
    jboolean result = false;
    const char *OS_DEBUGGABLE_STRING_VALUE = "1";
    const char *const ANDROID_OS_DEBUGGABLE_PROPERTIES = "ro.debuggable";
    // TODO try-catch
    jstring osDebuggableString = charStringToJString(env, OS_DEBUGGABLE_STRING_VALUE);
    result = searchStringIsInSystemProperties(env,
                                              osDebuggableString,
                                              ANDROID_OS_DEBUGGABLE_PROPERTIES);
    // TODO clean up
    return jBooleanToBooleanJObject(env, result);
}

/**
 * checkPermissiveSELinux() JNI function
 * checks system properties to find if OS is permissive SeLinux
 */
extern "C"
JNIEXPORT jobject JNICALL
Java_p_l_e_x_u_s_sdk_root_permissive_1selinux_SeLinux_00024Companion_isPermissiveSeLinux(
        JNIEnv *env,
        jobject companionObject
) {
    jboolean result = false;
    const char *PERMISSIVE_SE_LINUX_VALUE = "0";
    const char *const ANDROID_OS_BUILD_SELINUX_PROPERTIES = "ro.build.selinux";
    // TODO try-catch
    jstring permissiveSELinuxString = charStringToJString(env, PERMISSIVE_SE_LINUX_VALUE);
    result = searchStringIsInSystemProperties(env,
                                              permissiveSELinuxString,
                                              ANDROID_OS_BUILD_SELINUX_PROPERTIES);
    // TODO clean up
    return jBooleanToBooleanJObject(env, result);
}

/**
 * hasRootAdb() JNI function
 * checks if ADB service with root access is present in system
 */
extern "C"
JNIEXPORT jobject JNICALL
Java_p_l_e_x_u_s_sdk_root_root_1adb_RootAdbService_00024Companion_hasRootAdb(
        JNIEnv *env,
        jobject companionObject
) {
    jboolean result = false;
    const char *ADB_SERVICE_WITH_ROOT_ACCESS_VALUE = "1";
    const char *const SERVICE_ADB_ROOT_PROPERTIES = "service.adb.root";
    // TODO try-catch
    jstring rootAdbValueString = charStringToJString(env, ADB_SERVICE_WITH_ROOT_ACCESS_VALUE);
    result = searchStringIsInSystemProperties(env,
                                              rootAdbValueString,
                                              SERVICE_ADB_ROOT_PROPERTIES);
    // TODO clean up
    return jBooleanToBooleanJObject(env, result);
}

/**
 * osInsecure() JNI function
 * checks system properties to find if OS_SECURE property has inappropriate value
 */
extern "C"
JNIEXPORT jobject JNICALL
Java_p_l_e_x_u_s_sdk_root_os_1insecure_OsInsecure_00024Companion_osInsecure(
        JNIEnv *env,
        jobject companionObject
) {
    jboolean result = false;
    const char *ANDROID_OS_INSECURE_VALUE = "0";
    const char *const ANDROID_OS_SECURE = "ro.secure";
    // TODO try-catch
    jstring osInsecureString = charStringToJString(env, ANDROID_OS_INSECURE_VALUE);
    result = searchStringIsInSystemProperties(env,
                                              osInsecureString,
                                              ANDROID_OS_SECURE);
    // TODO clean up
    return jBooleanToBooleanJObject(env, result);
}

/**
 * sysInitd() JNI function
 * checks system properties to find sys initd
 */
extern "C"
JNIEXPORT jobject JNICALL
Java_p_l_e_x_u_s_sdk_root_sys_1initd_SysInitd_00024Companion_sysInitd(
        JNIEnv *env,
        jobject companionObject
) {
    jboolean result = false;
    const char *SYS_INITD_VALUE = "1";
    const char *const ANDROID_OS_SYS_INITD_PROPERTIES = "sys.initd";
    // TODO try-catch
    jstring hasSysInitdString = charStringToJString(env, SYS_INITD_VALUE);
    result = searchStringIsInSystemProperties(env,
                                              hasSysInitdString,
                                              ANDROID_OS_SYS_INITD_PROPERTIES);
    // TODO clean up
    return jBooleanToBooleanJObject(env, result);
}

/**
 * Checks if file is accessed
 * @param env - JNI environment
 * @param pathToFileCharString - C type char string containing path to file
 * @return - jboolean
 */
jboolean isAccessedFile(
        JNIEnv *env,
        const char *pathToFileCharString
) {
    jboolean result = false;
    // logD(env, pathToFileCharString);
    int code = access(pathToFileCharString, F_OK);
    result = code == 0;
    if (result) {
        // logD(env, "File was accessed");
    }
    // TODO clean up
    return result;
}

/**
 * isAccessedFile() JNI function
 */
extern "C"
JNIEXPORT jobject JNICALL
Java_p_l_e_x_u_s_sdk_root_accessed_FileAccessed_00024Companion_isAccessedFile(
        JNIEnv *env,
        jobject companionObject,
        jstring pathToFile
) {
    jboolean result = false;
    // TODO try-catch
    std::string pathToFileStdString = jstringToStdString(env, pathToFile);
    const char *pathToFileCharString = pathToFileStdString.c_str();
    result = isAccessedFile(env, pathToFileCharString);
    // TODO clean up
    return jBooleanToBooleanJObject(env, result);
}

/**
 * isBinaryFound() JNI function
 */
extern "C"
JNIEXPORT jobject JNICALL
Java_p_l_e_x_u_s_sdk_root_binary_FindBinary_00024Companion_isBinaryFound(
        JNIEnv *env,
        jobject companionObject,
        jstring binaryName
) {
    const char *const PATHS_ARRAY[] = {
            "/data/local/",
            "/data/local/bin/",
            "/data/local/xbin/",
            "/sbin/",
            "/system/bin/",
            "/system/bin/.ext/",
            "/system/bin/failsafe/",
            "/system/sd/xbin/",
            "/su/xbin/",
            "/su/bin/",
            "/magisk/.core/bin/",
            "/system/usr/we-need-root/",
            "/system/xbin/",
            0 // NOLINT(modernize-use-nullptr)
    };
    jboolean result = false;
    // TODO try-catch
    std::string binaryNameStdString = jstringToStdString(env, binaryName);
    const char *binaryNameCharString = binaryNameStdString.c_str();
    for (size_t i = 0; PATHS_ARRAY[i]; ++i) {
        char *checkedPathCharString = concatenateCharStrings(
                PATHS_ARRAY[i],
                binaryNameCharString
        );
        if (checkedPathCharString == nullptr) { // malloc failed
            result = false;
            break;
        }
        result = isAccessedFile(env, checkedPathCharString);
        free(checkedPathCharString);
        if (result) break;
    }
    // TODO clean up
    return jBooleanToBooleanJObject(env, result);
}

/**
 * findHooks() JNI function
 */
extern "C"
JNIEXPORT jobject JNICALL
Java_p_l_e_x_u_s_sdk_root_hooks_FindHooks_00024Companion_findHooks(
        JNIEnv *env,
        jobject companionObject
) {
    // TODO try-catch
    jboolean result = false;

    pid_t pid = getpid();
    char maps_file_name[512];
    sprintf(maps_file_name, "/proc/%d/maps", pid);
    // logging
    // logD(env, "try to open:");
    // logD(env, maps_file_name);
    const size_t line_size = BUFSIZ;
    char *line = (char *) malloc(line_size);
    if (line == nullptr) {
        return jBooleanToBooleanJObject(env, result);
    }
    FILE *fp = fopen(maps_file_name, "r");
    if (fp == nullptr) {
        free(line);
        return jBooleanToBooleanJObject(env, result);
    }
    memset(line, 0, line_size);

    const char *substrate = "com.saurik.substrate";
    const char *xposed = "XposedBridge.jar";

    while (fgets(line, line_size, fp) != nullptr) {
        const size_t real_line_size = strlen(line);
        if ((real_line_size >= strlen(substrate) && strstr(line, substrate) != nullptr) ||
            (real_line_size >= strlen(xposed) && strstr(line, xposed) != nullptr)) {
            // logging
            // logD(env, "found: ");
            // logD(env, line);
            result = true;
            break;
        }
    }
    free(line);
    fclose(fp);

    // TODO clean up
    return jBooleanToBooleanJObject(env, result);
}