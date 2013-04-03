package vn.edu.rmit.Utilities;

/**
 * Created with IntelliJ IDEA.
 * User: KATE.LE
 * Date: 3/16/13
 * Time: 5:13 PM
 */
public enum GroundType {
    GRASS("Grass", ImagePath.GRASS),
    MOUNTAIN("Mountain", ImagePath.MOUNTAIN),
    HOLE("Hole", ImagePath.HOLE),
    WATER("Water", ImagePath.WATER),
    TREE("Tree", ImagePath.TREE),
    WARRIOR1("Warrior1", ImagePath.W1),
    WARRIOR2("Warrior1", ImagePath.W2),
    MOVE_AVAIL("Move Available", ImagePath.MOVE_AVAIL);

    private String name;
    private String imagePath;
    private GroundType(String name, String imagePath){
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getImagePath(){
        return this.imagePath;
    }
}
