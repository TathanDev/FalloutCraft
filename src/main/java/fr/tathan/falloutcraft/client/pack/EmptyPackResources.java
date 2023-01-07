package fr.tathan.falloutcraft.client.pack;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

public class EmptyPackResources implements PackResources {

    @Nullable
    @Override
    public InputStream getRootResource(String pFileName) throws IOException {
        return null;
    }

    @Override
    public InputStream getResource(PackType pType, ResourceLocation pLocation) throws IOException {
        throw new FileNotFoundException();
    }

    @Override
    public Collection<ResourceLocation> getResources(PackType pType, String pNamespace, String pPath, Predicate<ResourceLocation> pFilter) {
        return Collections.emptyList();
    }

    @Override
    public boolean hasResource(PackType pType, ResourceLocation pLocation) {
        return false;
    }

    @Override
    public Set<String> getNamespaces(PackType pType) {
        return Collections.emptySet();
    }

    @Nullable
    @Override
    public <T> T getMetadataSection(MetadataSectionSerializer<T> pDeserializer) throws IOException {
        return null;
    }

    @Override
    public String getName() {
        return "FalloutCraft Marker";
    }

    @Override
    public void close() {

    }
}
