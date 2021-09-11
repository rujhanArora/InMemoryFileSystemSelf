package com.machineCoding.InMemoryFileSystemSelf.models;

public class RootDirectory extends Directory {
    RootDirectory() {
        super(null, "root");
    }

    @Override
    public void delete() {
        throw new RuntimeException("Unsupported action: DELETE on root");
    }

    @Override
    public void ls() {
        System.out.println("Type: directory; Name: " + this.name);
        this.entries.forEach(entry -> entry.ls());
    }
}
