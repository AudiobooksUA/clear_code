public abstract class Employee{
  public abstract boolean isPatday();
  public abstract Money calculatePay();
  public abstract void deliverPay(Money pay);
}
-------------------
public interface EmployeeFactory{
  public Employee makeEmployee(EmployeeRecord r) throws InvalidEmployeeType;
}
-------------------
public class EmployeeFactoryImpl implements EmployeeFactory{
  public Emploee makeEmployee(EmployeeRecord r) throws InvalidEmployeeType {
    switch (r.type){
      case COMMISSIONED:
        return new CommissionedEmployee(r);
      case HOURLY:
        return new HourlyEmployee(r);
      case SALARIED:
        rreturn new SalariedEmployee(r);
      default:
        throw new InvalidEmployeeType(r.type);
    }
  }
}
