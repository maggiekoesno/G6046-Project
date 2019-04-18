public class Block{
    private int id;
    private boolean isRoom;
    private boolean isSpecial;
    private boolean isEntrypoint;
    private boolean isStartpoint;
    private boolean hasPlayer;
    private String roomName;

    public Block(int i, boolean r, boolean s, String n){
        id = i;
        isRoom = r;
        isSpecial = s;
        isEntrypoint = false;
        isStartpoint = false;
        hasPlayer = false;
        roomName = n;
    }

    public int getId(){
        return id;
    }

    public boolean isRoom(){
        return isRoom;
    }

    public void makeSpecial(){
        this.isSpecial = true;
    }

    public boolean isSpecial(){
        return isSpecial;
    }

    public boolean isEntrypoint(){
        return isEntrypoint;
    }

    public void makeStartpoint(){
        isStartpoint = true;
    }

    public boolean isStartpoint(){
        return isStartpoint;
    }

    public void setHasPlayer(boolean b){
        hasPlayer = b;
    }

    public boolean hasPlayer(){
        return hasPlayer;
    }

    public String getRoomName(){
        return roomName;
    }

    public boolean canMove(int nextBlock){
        Block next = Board.blockSearch(nextBlock);
        if(this.isRoom == next.isRoom){
            return true;
        }
        return this.isEntrypoint;
    }

}