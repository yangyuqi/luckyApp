package com.yunqilai.delivery.manager;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 本地文件存储对象。由于各方法可能会耗时，客户端在使用的时候，尽量不要在 UI Thread 调用。
 * 1、存储位置为内部存储，目录为 emulated/0/Android/data/packageName/files，应用卸载，数据被清除。
 * 2、存储位置为外部存储，目录为 emulated/0/.../parentDirPath，应用卸载，数据不被清除。
 * <p/>
 */
public class FileStorage {

    /**
     * 返回私有目录的目标操作文件
     *
     * @param context  上下文对象
     * @param fileName 文件名
     * @return
     */
    private File getFile(Context context, String fileName) {
        try {
            String externalDirPath = context.getExternalFilesDir(null).getAbsolutePath();
            String destFilePath = externalDirPath + File.separator + fileName;
            return new File(destFilePath);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 在私有目录存储数据到指定文件，externalStorage 中的私有目录，模式为 Context.MODE_PRIVATE。
     *
     * @param context  上下文对象
     * @param fileName 文件名
     * @param data     数据流
     * @return true-保存成功，false-保存失败
     */
    public boolean savePrivate(Context context, String fileName, byte[] data) {
        try {
            File destFile = getFile(context, fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(destFile);
            fileOutputStream.write(data);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * 在私有目录存储对象到指定文件，externalStorage 中的私有目录，模式为 Context.MODE_PRIVATE。
     *
     * @param context  上下文对象
     * @param fileName 文件名
     * @param object   待保存对象
     * @return true-保存成功，false-保存失败
     */
    public boolean savePrivate(Context context, String fileName, Serializable object) {
        try {
            File destFile = getFile(context, fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(destFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * 删除文件
     *
     * @param context  上下文对象
     * @param fileName 文件名
     * @return true-删除成功，false-删除失败
     */
    public boolean deletePrivate(Context context, String fileName) {
        File destFile = getFile(context, fileName);
        if(destFile != null){
            return destFile.delete();
        }else{
            return false;
        }
    }

    /**
     * 从私有目录读取普通文件数据
     *
     * @param context  上下文对象
     * @param fileName 文件名
     * @return 文件内容
     */
    public byte[] readPrivateFile(Context context, String fileName) {
        byte[] data = null;
        try {
            File destFile = getFile(context, fileName);
            FileInputStream fileInputStream = new FileInputStream(destFile);
            data = new byte[fileInputStream.available()];
            fileInputStream.read(data);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    /**
     * 从私有目录读取对象
     *
     * @param context  上下文对象
     * @param fileName 文件名
     * @return 对象
     */
    public <T> T readPrivateObject(Context context, String fileName, Class<T> clsOfT) {
        Object object = null;
        try {
            File destFile = getFile(context, fileName);
            FileInputStream fileInputStream = new FileInputStream(destFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        }
        catch (FileNotFoundException notFindE){
            Log.d("FileStorage",fileName+" file can not found ");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return (T) object;
    }

    /**
     * 在公有目录存储数据到指定文件，
     *
     * @param parentDirPath 目录路径
     * @param fileName      文件名
     * @param data          待存储数据
     * @return true-保存成功，false-保存失败
     */
    public boolean savePublic(String parentDirPath, String fileName, byte[] data) {
        File file = new File(parentDirPath + File.separator + fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 在公有目录存储对象到指定文件
     *
     * @param parentDirPath 目录路径
     * @param fileName      文件名
     * @param object        待存储对象
     * @return true-保存成功，false-保存失败
     */
    public boolean savePublic(String parentDirPath, String fileName, Serializable object) {
        File file = new File(parentDirPath + File.separator + fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 删除公有目录文件
     *
     * @param parentDirPath 目录路径
     * @param fileName      文件名
     * @return true-删除成功，false-删除失败
     */
    public boolean deletePublic(String parentDirPath, String fileName) {
        File file = new File(parentDirPath + File.separator + fileName);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 从公有目录读取文件内容
     *
     * @param parentDirPath 目录路径
     * @param fileName      文件名
     * @return 文件数据
     */
    public byte[] readPublicFile(String parentDirPath, String fileName) {
        File file = new File(parentDirPath + File.separator + fileName);
        byte[] data = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            data = new byte[fileInputStream.available()];
            fileInputStream.read(data);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    /**
     * 从公有目录读取对象内容
     *
     * @param parentDirPath 目录路径
     * @param fileName      文件名
     * @return 对象
     */
    public <T> T readPublicObject(String parentDirPath, String fileName, Class<T> clsOfT) {
        File file = new File(parentDirPath + File.separator + fileName);
        Object object = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) object;
    }

}
