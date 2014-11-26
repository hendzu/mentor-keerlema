package ee.ut.math.tvt.salessystem.domain.data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SALE")
public class OrderHistoryItem implements Cloneable, DisplayableItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="DATE")
	private Timestamp time;
	@Column(name="PRICE")
	private double price;
	
	@OneToMany(mappedBy ="history")
	private List<SoldItem> items;
	
	public OrderHistoryItem() {
		items = new ArrayList<SoldItem>();
	}

	public OrderHistoryItem(double price, List<SoldItem> list) {
		this.items = list;
		this.price = getSum();
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		this.time = new Timestamp(now.getTime());
	}

	public List<SoldItem> getItems() {
		return items;
	}

	public void setItems(List<SoldItem> items) {
		this.items = items;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return time;
	}
	
	public void addSoldItem(SoldItem item){
		if(item == null){
			throw new NullPointerException();
		}
		else{
			items.add(item);
		}
	}
	
	public double getSum(){
		double sum = 0;
		for(SoldItem item: items){
			sum += item.getSum();
		}
		return sum;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public double getPrice() {
		return getSum();
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		return ((OrderHistoryItem)obj).getId() == this.getId();
	}

}
