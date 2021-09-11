package com.machineCoding.InMemoryFileSystemSelf.utils;

public class PathUtils {
    public static String[] getNodesArray(String path) {
        return path.split("/");
    }
}
