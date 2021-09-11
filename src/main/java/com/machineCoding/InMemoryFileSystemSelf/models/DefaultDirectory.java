package com.machineCoding.InMemoryFileSystemSelf.models;

public class DefaultDirectory extends Directory {

    DefaultDirectory(Directory parent, String name) {
        super(parent, name);
    }

    @Override
    public void ls() {
        System.out.println("Type: directory; Name: " + this.name + " inside " + this.parent.name);
        this.entries.forEach(entry -> entry.ls());
    }

    @Override
    public void delete() {
        for (IEntry iEntry: this.entries) {
            iEntry.delete();
        }
        this.parent = null;
    }
}
