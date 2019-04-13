public class Block{
    private int id;
    private boolean isRoom;
    private boolean isSpecial;
    private boolean isEntrypoint;
    private boolean isStartpoint;
    private String roomName;

    public Block(int i, boolean r, boolean s, String n){
        id = i;
        isRoom = r;
        isSpecial = s;
        isEntrypoint = false;
        isStartpoint = false;
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

    public String getRoomName(){
        return roomName;
    }

    public boolean canMove(Block next){
        if(this.isRoom == next.isRoom){
            return true;
        }
        return this.isEntrypoint;
    }

}