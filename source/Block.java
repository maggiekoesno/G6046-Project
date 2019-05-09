/**
 * Block class represents a each block located on the board.
 */
public class Block{

    /**
     * Location id of the block.
     * Will be used to help navigate players accross the board.
     */
    private int id;

    /**
     * Indicates if the block is inside a room.
     */
    private boolean isRoom;

    /**
     * Indicates if the block is a special block.
     */
    private boolean isSpecial;

    /**
     * Indicates if the block is an entry point.
     */
    private boolean isEntrypoint;

    /**
     * Indicates if the block is a start block.
     */
    private boolean isStartpoint;

    /**
     * Indicates if the block is occupied by a player.
     */
    private boolean hasPlayer;

    /**
     * Name of room block is in.
     */
    private String roomName;

    /**
     * Block constructor method.
     * 
     * @param i id
     * @param r isRoom
     * @param s isSpecial
     * @param n name of room
     */
    public Block(int i, boolean r, boolean s, String n){
        id = i;
        isRoom = r;
        isSpecial = s;
        isEntrypoint = false;
        isStartpoint = false;
        hasPlayer = false;
        roomName = n;
    }

    /**
     * Getter method for id.
     * 
     * @return location id
     */
    public int getId(){
        return id;
    }

    /**
     * Getter method for isRoom.
     * 
     * @return true if in room, false otherwise.
     */
    public boolean isRoom(){
        return isRoom;
    }

    /**
     * Set a block to be special
     */
    public void makeSpecial(){
        this.isSpecial = true;
    }

    /**
     * Getter method for isSpecial.
     * 
     * @return true if special, false otherwise
     */
    public boolean isSpecial(){
        return isSpecial;
    }

    /**
     * Getter method for isEntrypoint.
     * 
     * @return true if is entrypoint, false otherwise
     */
    public boolean isEntrypoint(){
        return isEntrypoint;
    }

    /**
     * Make block a starting point.
     */
    public void makeStartpoint(){
        isStartpoint = true;
    }

    /**
     * Getter method for isStartpoint.
     * 
     * @return true if is startblock, false otherwise
     */
    public boolean isStartpoint(){
        return isStartpoint;
    }

    /**
     * Setter method for hasPlayer.
     * 
     * @param b true if block is now occupied by player, false otherwise
     */
    public void setHasPlayer(boolean b){
        hasPlayer = b;
    }

    /**
     * Getter method for hasPlayer.
     * 
     * @return true if block is occupied by player, false otherwise
     */
    public boolean hasPlayer(){
        return hasPlayer;
    }

    /**
     * Getter method for room name.
     * 
     * @return name of room
     */
    public String getRoomName(){
        return roomName;
    }

    /**
     * Indicates if player can move from this block to an adjacent block.
     * Player can only move from outside a room into a room or vice versa through a door.
     * Player can not move to a block that already has a player on it.
     * 
     * @param nextBlock location id of block player want to move to
     * @return          true if player can move to that block, false otherwise
     */
    public boolean canMove(int nextBlock){
        Block next = Board.blockSearch(nextBlock);

        if(next.hasPlayer) return false;
        if(this.isRoom == next.isRoom){
            return true;
        }
        return this.isEntrypoint;
    }

}