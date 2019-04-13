import java.util.ArrayList;
import java.util.Random;

public final class Board{
    
    private static ArrayList<Block> blockList = new ArrayList<Block>();
    private static ArrayList<Integer> startBlock = new ArrayList<Integer>();
    private static int count;

    private static void roomBlock(int blocks, String name, boolean isEntryPoint){
        for(int i = 0; i < blocks; i++){
            blockList.add(new Block(count, true, isEntryPoint, name));
            count++;
        }
    }

    private static void outBlock(int blocks, boolean isEntryPoint){
        for(int i = 0; i < blocks; i++){
            blockList.add(new Block(count, false, isEntryPoint, null));
            count++;
        }
    }

    public static Block blockSearch(int id){
        int max = blockList.size() - 1;
        int min = 0;
        int mid;
        int currId;

        while(true){
            if(max < min){
                return null;
            }

            mid = (int) max / 2;
            currId = blockList.get(mid).getId();

            if(currId < id)
                min = mid + 1;
            else if(currId > id)
                max = mid - 1;
            else if(currId == id)
                return blockList.get(mid);
        }
    }

    public static int getStartBlockSize(){
        return startBlock.size();
    }

    public static int getStartBlock(int i){
        int block = startBlock.get(i);
        startBlock.remove(i);
        return block;
    }
    
    public static void makeBoard(){
        Random r = new Random();
        Block block;
        int i = 12;
        int randId;

        for(int j = 0; j<3; j++){
            roomBlock(7, "Study", false);
            outBlock(3, false);
            roomBlock(5, "Hall", false);
            outBlock(3, false);
            roomBlock(7, "Lounge", false);
        }
        
        roomBlock(6, "Study", false);
        roomBlock(1, "Study", true);
        outBlock(3, false);
        roomBlock(5, "Hall", false);
        outBlock(3, false);
        roomBlock(7, "Lounge", false);

        outBlock(6, false);
        outBlock(1, true);
        outBlock(2, false);
        outBlock(1, true);
        roomBlock(1, "Hall", true);
        roomBlock(4, "Hall", false);
        outBlock(3, false);
        roomBlock(7, "Lounge", false);

        outBlock(10, false);
        roomBlock(5, "Hall", false);
        outBlock(3, false);
        roomBlock(1, "Lounge", true);
        roomBlock(6, "Lounge", false);

        //TODOnext row 7

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

        while(i>0){
            randId = r.nextInt(256)+1;
            block = blockSearch(randId);
            if( (!(block.isEntrypoint())) && (!(block.isRoom())) && (!(block.isEntrypoint()))){
                block.makeSpecial();
                i--;
            }
        }
    }
}