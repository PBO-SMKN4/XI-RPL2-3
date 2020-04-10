package tugas.model;

/**
 *
 * @author Fadillah
 */
public class tblPengembalianModel {
    private String nameItem;
    private String category;
    private String qty;
    
    public tblPengembalianModel(String nameItem,String category, String qty){
        this.nameItem = nameItem;
        this.category = category;
        this.qty = qty;
    }

    /**
     * @return the nameItem
     */
    public String getNameItem() {
        return nameItem;
    }

    /**
     * @param nameItem the nameItem to set
     */
    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    /**
     * @return the categoty
     */
   

    /**
     * @return the qty
     */
    public String getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(String qty) {
        this.qty = qty;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    
}
