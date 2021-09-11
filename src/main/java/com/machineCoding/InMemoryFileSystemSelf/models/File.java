package com.machineCoding.InMemoryFileSystemSelf.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@NoArgsConstructor
public class File implements IEntry {
    private String name;
    private String content;
    private Date creationTime;
    private Directory parent;
    private final EntryType entryType = EntryType.FILE;

    File(Directory parent, String name) {
        this.parent = parent;
        this.name = name;
        this.content = "";
        this.creationTime = new Date();
    }

    public void addContent(String content) {
        this.content = this.content + content;
    }

    public String getContent() {
        return this.content;
    }

    @Override
    public void changeName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getNoOfFiles() {
        return 1;
    }

    @Override
    public long getSizeInBytes() {
        return this.content.length();
    }

    @Override
    public Date getCreationTime() {
        return creationTime;
    }

    @Override
    public void delete() {
        this.parent = null;
    }

    @Override
    public void ls() {
        System.out.println("Type: File; Name: " + this.name + " inside " + this.parent.name);
    }

    @Override
    public EntryType getEntryType() {
        return entryType;
    }
}
