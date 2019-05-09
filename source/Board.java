import java.util.ArrayList;
import java.util.Random;

/**
 * Board class represents the board used in the game.
 * Class is final because there can only be one instance of the board in a game.
 */
public final class Board{
    
    /**
     * Array list of all the blocks that make up the board.
     */
    private static ArrayList<Block> blockList = new ArrayList<Block>();

    /**
     * Array list of all the start block's location id.
     * Startblocks are specific locations on the board where the players can start the game from.
     */
    private static ArrayList<Integer> startBlock = new ArrayList<Integer>();

    /**
     * Counter to help keep track of block id when creating blocks during creation of board.
     * Initialized to zero.
     */
    private static int count = 0;

    /**
     * Create a block that is part of a room.
     * 
     * @param blocks       number of consecutive blocks to make
     * @param name         name of room
     * @param isEntryPoint true if it is connected to a door, false otherwise
     */
    private static void roomBlock(int blocks, String name, boolean isEntryPoint){
        for(int i = 0; i < blocks; i++){
            count++;
            blockList.add(new Block(count, true, isEntryPoint, name));
        }
    }

    /**
     * Create a block that is not part of a room.
     * 
     * @param blocks       number of consecutive blocks to make
     * @param isEntryPoint true if it is connected to a door, false otherwise
     */
    private static void outBlock(int blocks, boolean isEntryPoint){
        for(int i = 0; i < blocks; i++){
            count++;
            blockList.add(new Block(count, false, isEntryPoint, null));
        }
    }

    /**
     * Retrieve block object based on id.
     * 
     * @param id id of block to search
     * @return   block object
     */
    public static Block blockSearch(int id){
        int max = blockList.size() - 1;
        int min = 0;
        int mid;
        int currId;
        while(true){
            if(max < min){
                return null;
            }

            mid = (int) (min+max) / 2;
            currId = blockList.get(mid).getId();
            
            if(currId < id){
                min = mid + 1;
            }
            else if(currId > id)
                max = mid - 1;
            else if(currId == id)
                return blockList.get(mid);
        }
    }

    /**
     * Method to get number of start blocks unoccupied left.
     * 
     * @return size of startBlock array list
     */
    public static int getStartBlockSize(){
        return startBlock.size();
    }

    /**
     * Method to retrieve location id of a start block.
     * Removes the start block from the array list after because
     * we assume the block will then be occupied by a player.
     * 
     * @param i index number
     * @return location id of start block
     */
    public static int getStartBlock(int i){
        int block = startBlock.get(i);
        startBlock.remove(i);
        return block;
    }
    
    /**
     * Create all the blocks needed to create the board.
     */
    public static void makeBoard(){
        Random r = new Random();
        Block block;
        int i = 12;
        int j;
        int randId;

        // Creation of first three rows
        for(j = 0; j<3; j++){
            roomBlock(7, "Study", false);
            outBlock(3, false);
            roomBlock(5, "Hall", false);
            outBlock(3, false);
            roomBlock(7, "Lounge", false);
        }
        
        // Creation of 4th row
        roomBlock(6, "Study", false);
        roomBlock(1, "Study", true);
        outBlock(3, false);
        roomBlock(5, "Hall", false);
        outBlock(3, false);
        roomBlock(7, "Lounge", false);

        // Creation of 5th row
        outBlock(6, false);
        outBlock(1, true);
        outBlock(2, false);
        outBlock(1, true);
        roomBlock(1, "Hall", true);
        roomBlock(4, "Hall", false);
        outBlock(3, false);
        roomBlock(7, "Lounge", false);

        // Creation of 6th row
        outBlock(10, false);
        roomBlock(5, "Hall", false);
        outBlock(3, false);
        roomBlock(1, "Lounge", true);
        roomBlock(6, "Lounge", false);

        // Creation of 7th row
        roomBlock(6, "Library", false);
        outBlock(4, false);
        roomBlock(2, "Hall", false);
        roomBlock(1, "Hall", true);
        roomBlock(2, "Hall", false);
        outBlock(3, false);
        outBlock(1, true);
        outBlock(6, false);

        // Creation of 8th row
        roomBlock(7, "Library", false);
        outBlock(5, false);
        outBlock(1, true);
        outBlock(12, false);

        // Creation of 9th row
        roomBlock(6, "Library", false);
        roomBlock(1, "Library", true);
        outBlock(1, true);
        outBlock(10, false);
        outBlock(1, true);
        outBlock(6, false);

        // Creation of 10th row
        roomBlock(7, "Library", false);
        outBlock(3, false);
        count = count + 4;
        outBlock(3, false);
        roomBlock(1, "Dining Room", false);
        roomBlock(1, "Dining Room", true);
        roomBlock(6, "Dining Room", false);

        // Creation of 11th row 
        roomBlock(2, "Library", false);
        roomBlock(1, "Library", true);
        roomBlock(3, "Library", false);
        outBlock(4, false);
        count = count + 4;
        outBlock(3, false);
        roomBlock(8, "Dining Room", false);

        // Creation of 12th row
        outBlock(1, true);
        outBlock(1, false);
        outBlock(1, true);
        outBlock(7, false);
        count = count + 4;
        outBlock(3, false);
        roomBlock(8, "Dining Room", false);

        // Creation of 13th row
        roomBlock(1, "Billiard Room", true);
        roomBlock(4, "Billiard Room", false);
        outBlock(5, false);
        count = count + 4;
        outBlock(2, false);
        outBlock(1, true);
        roomBlock(1, "Dining Room", true);
        roomBlock(7, "Dining Room", true);
        
        // Creation of 14th row
        roomBlock(5, "Billiard Room", false);
        outBlock(5, false);
        count = count + 4;
        outBlock(3, false);
        roomBlock(8, "Dining Room", false);
        
        // Creation of 15th row
        roomBlock(5, "Billiard Room", false);
        outBlock(5, false);
        count = count + 4;
        outBlock(3, false);
        roomBlock(8, "Dining Room", false);
    
        // Creation of 16th row
        roomBlock(4, "Billiard Room", false);
        roomBlock(1, "Billiard Room", true);
        outBlock(1, true);
        outBlock(14, false);
        roomBlock(5, "Dining Room", false);

        // Creation of 17th row
        roomBlock(5, "Billiard Room", false);
        outBlock(5, false);
        outBlock(1, true);
        outBlock(3, false);
        outBlock(1, true);
        outBlock(10, false);

        // Creation of 18th row
        outBlock(9, false);
        roomBlock(1, "Ball Room", false);
        roomBlock(1, "Ball Room", true);
        roomBlock(3, "Ball Room", false);
        roomBlock(1, "Ball Room", true);
        roomBlock(1, "Ball Room", false);
        outBlock(4, false);
        outBlock(1, true);
        outBlock(4, false);
        
        // Creation of 19th row
        outBlock(9, false);
        roomBlock(7, "Ball Room", false);
        outBlock(3, false);
        roomBlock(1, "Kitchen", false);
        roomBlock(1, "Kitchen", true);
        roomBlock(4, "Kitchen", false);

        // Creation of 20th row
        roomBlock(4, "Conservatory", false);
        roomBlock(1, "Conservatory", true);
        outBlock(1, true);
        outBlock(2, false);
        outBlock(1, true);
        roomBlock(1, "Ball Room", true);
        roomBlock(6, "Ball Room", false);
        outBlock(3, false);
        roomBlock(6, "Kitchen", false);

        // Creation of 21st - 23rd row
        for (j = 0; j<3; j++){
            roomBlock(6, "Conservatory", false);
            outBlock(3, false);
            roomBlock(7, "Ball Room", false);
            outBlock(3, false);
            roomBlock(6, "Kitchen", false);
        }

        // Creation of 24th row
        roomBlock(6, "Conservatory", false);
        outBlock(5, false);
        roomBlock(3, "Ball Room", false);
        outBlock(5, false);
        roomBlock(6, "Kitchen", false);
        
        // Creation of 25th row
        roomBlock(6, "Conservatory", false);
        outBlock(5, false);
        roomBlock(3, "Ball Room", false);
        outBlock(5, false);
        roomBlock(6, "Kitchen", false);

        // Initialize start blocks
        startBlock.add(18);
        startBlock.add(126);
        startBlock.add(200);
        startBlock.add(451);
        startBlock.add(611);
        startBlock.add(615);

        for(Integer id : startBlock){
            block = blockSearch(id);
            block.makeStartpoint();
        }
        
        // Initialize random special blocks
        while(i>0){
            randId = r.nextInt(256)+1;
            block = blockSearch(randId);
            if( (!(block.isEntrypoint())) && (!(block.isRoom())) && (!(block.isStartpoint()))){
                block.makeSpecial();
                i--;
            }
        }
    }
}