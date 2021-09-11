package com.machineCoding.InMemoryFileSystemSelf.models;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface IEntry {
    void changeName(String name);
    String getName();
    long getNoOfFiles();
    long getSizeInBytes();
    Date getCreationTime();
    void delete();
    void ls();
    EntryType getEntryType();
}
