package com.khorn.terraincontrol;

import com.khorn.terraincontrol.configuration.BiomeConfig;
import com.khorn.terraincontrol.configuration.WorldSettings;
import com.khorn.terraincontrol.customobjects.CustomObjectStructureCache;
import com.khorn.terraincontrol.generator.biome.OutputType;
import com.khorn.terraincontrol.util.NamedBinaryTag;
import com.khorn.terraincontrol.util.minecraftTypes.TreeType;

import java.util.List;
import java.util.Random;

public interface LocalWorld
{

    // Biome init
    public LocalBiome addCustomBiome(String name, BiomeIds id);

    public LocalBiome getNullBiome(String name);

    // With static id allocation this is not a required feature.
    public int getMaxBiomesCount();

    public int getFreeBiomeId();

    public LocalBiome getBiomeById(int id);

    public int getBiomeIdByName(String name);

    public List<? extends LocalBiome> getDefaultBiomes();

    // Biome manager

    /**
     * Calculate biome ids array used in terrain generation.
     * 
     * @param biomeArray Output array. If it is null or wrong size return new
     *            array.
     * @param x The block x.
     * @param z The block z.
     * @param x_size Size of block in x coordinate.
     * @param z_size Size of blocks in z coordinate.
     * @param type Output type. May be FULL, WITHOUT_RIVERS, ONLY_RIVERS or
     *            DEFAULT_FOR_WORLD.
     * @return Array filled by biome ids.
     */
    public int[] getBiomesUnZoomed(int[] biomeArray, int x, int z, int x_size, int z_size, OutputType type);

    public int[] getBiomes(int[] biomeArray, int x, int z, int x_size, int z_size, OutputType type);

    public int getCalculatedBiomeId(int x, int z);

    /**
     * Calculates the biome that should generate at the given coordinates.
     * 
     * @param x The block x.
     * @param z The block z.
     * @return The biome at the given coordinates.
     */
    @SuppressWarnings("UnusedDeclaration")
    public LocalBiome getCalculatedBiome(int x, int z);

    public int getBiomeId(int x, int z);

    /**
     * Gets the (stored) biome at the given coordinates.
     * 
     * @param x The block x.
     * @param z The block z.
     * @return The biome at the given coordinates.
     */
    public LocalBiome getBiome(int x, int z);

    // temperature*rain
    public double getBiomeFactorForOldBM(int index);

    // Default generators
    public void prepareDefaultStructures(int chunkX, int chunkZ, boolean dry);

    public void PlaceDungeons(Random rand, int x, int y, int z);

    public boolean PlaceTree(TreeType type, Random rand, int x, int y, int z);

    public boolean placeDefaultStructures(Random rand, int chunkX, int chunkZ);

    public void replaceBlocks();

    public void replaceBiomes();

    /**
     * Since Minecraft Beta 1.8, friendly mobs are mainly spawned during the
     * terrain generation.
     */
    public void placePopulationMobs(BiomeConfig config, Random random, int chunkX, int chunkZ);

    // Blocks
    public LocalMaterialData getMaterial(int x, int y, int z);

    public boolean isEmpty(int x, int y, int z);

    public void setBlock(int x, int y, int z, LocalMaterialData material);

    public void attachMetadata(int x, int y, int z, NamedBinaryTag tag);

    @SuppressWarnings("UnusedDeclaration")
    public NamedBinaryTag getMetadata(int x, int y, int z);

    public int getLiquidHeight(int x, int z);

    /**
     * Returns the block above the highest solid block.
     */
    public int getSolidHeight(int x, int z);

    /**
     * Returns the block above the highest block.
     */
    public int getHighestBlockYAt(int x, int z);

    public void setChunksCreations(boolean createNew);

    public int getLightLevel(int x, int y, int z);

    public boolean isLoaded(int x, int y, int z);

    // Other information
    public WorldSettings getSettings();

    public CustomObjectStructureCache getStructureCache();

    public String getName();

    public boolean canBiomeManagerGenerateUnzoomed();

    public long getSeed();

    /**
     * Gets the height the base terrain of the world is capped at. Resources
     * ignore this limit.
     * 
     * @return The height the base terrain of the world is capped at.
     */
    public int getHeightCap();

    /**
     * Returns the vertical scale of the world. 128 blocks is the normal
     * scale, 256 doubles the scale, 64 halves the scale, etc. Only powers of
     * two will be returned.
     * 
     * @return The vertical scale of the world.
     */
    public int getHeightScale();
}
