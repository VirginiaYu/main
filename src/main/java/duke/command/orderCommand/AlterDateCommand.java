package duke.command.orderCommand;

import duke.command.Cmd;
import duke.exception.DukeException;
import duke.list.GenericList;
import duke.order.Order;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.Date;

/**
 * Represents a specific {@link Cmd} used to alter the {@link Order} serving date.
 */
public class AlterDateCommand extends Cmd<Order> {

    private int orderIndex;
    private Date date;

    /**
     * The constructor method for {@link AlterDateCommand}.
     *
     * @param orderNumber order index number, starting from 1, maximum orderList.size()
     * @param newDate new serving date of the {@link Order}
     */
    public AlterDateCommand(int orderNumber, Date newDate) {
        this.orderIndex = orderNumber;
        this.date = newDate;
    }

    @Override
    public void execute(GenericList<Order> orderList, Ui ui, Storage orderStorage) throws DukeException {
        if (orderList.size()==0) {
            throw new DukeException("No order in the list! No order can be altered!");
        }
        if (orderIndex < orderList.size() && orderIndex >= 0) {
            Order order = orderList.getEntry(orderIndex);
            if (order.isDone()) { throw new DukeException("Order done already. Date alteration is not expected."); }
            order.setDate(date);
            ui.showOrderChangedDate(order.getDate(),orderList.getEntry(orderIndex).toString());
            orderStorage.changeContent(orderIndex+1);

            // to do:
            // update today's dish(task) list if new date is today


        } else {
            throw new DukeException("Must enter a valid order index number between 1 and "+orderList.size());
        }
    }
}

