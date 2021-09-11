package com.machineCoding.InMemoryFileSystemSelf.models;

import com.machineCoding.InMemoryFileSystemSelf.utils.ValidationUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public abstract class Directory implements IDirectory {

    protected String name;
    protected Directory parent;
    protected List<IEntry> entries;
    protected Date creationTime;
    protected final EntryType entryType = EntryType.DIRECTORY;

    Directory(Directory parent, String name) {
        this.parent = parent;
        this.name = name;
        this.entries = new ArrayList<>();
        this.creationTime = new Date();
    }

    public Optional<IEntry> getEntryWithName(String name) {
        List<IEntry> entriesWithSameName = this.entries.stream()
                .filter(iEntry -> iEntry.getName().equals(name))
                .collect(Collectors.toList());
        return entriesWithSameName.isEmpty() ? Optional.empty() : Optional.of(entriesWithSameName.get(0));
    }

    @Override
    public long getNoOfFiles() {
        return entries.stream().map(IEntry::getNoOfFiles).reduce(0L, Long :: sum);
    }

    @Override
    public void deleteEntry(String name) {
        Optional<IEntry> iEntryOptional = getEntryWithName(name);
        ValidationUtils.ensureTrue(iEntryOptional.isPresent(),
                "Entry with : " + name + " does not exist in directory: " + this.name);
        IEntry iEntry = iEntryOptional.get();
        iEntry.delete();
        this.entries.remove(iEntry);
    }

    @Override
    public Directory addDirectory(String name) {
        ValidationUtils.ensureFalse(getEntryWithName(name).isPresent(),
                "Directory : " + name + " already exists!");
        Directory directory = new DefaultDirectory(this, name);
        entries.add(directory);
        return directory;
    }

    @Override
    public File addFile(String name) {
        ValidationUtils.ensureFalse(getEntryWithName(name).isPresent(),
                "File : " + name + " already exists!");
        File file = new File(this, name);
        entries.add(file);
        return file;
    }

    @Override
    public Iterator<IEntry> getEntries() {
        return this.entries.iterator();
    }

    @Override
    public long getSizeInBytes() {
        return entries.stream().map(IEntry::getSizeInBytes).reduce(0L, Long :: sum);
    }

    @Override
    public Date getCreationTime() {
        return creationTime;
    }

    @Override
    public abstract void ls();

    @Override
    public void changeName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public EntryType getEntryType() {
        return entryType;
    }
}
