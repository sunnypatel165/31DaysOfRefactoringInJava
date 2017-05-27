The refactoring today is pretty straightforward, although often overlooked and ignored as being a worthwhile refactoring. Move method does exactly what it sounds like, move a method to a better location. Let’s look at the following code before our refactoring:
```Java
   1: public class BankAccount
   2: {
   3:     public BankAccount(int accountAge, int creditScore, AccountInterest accountInterest)
   4:     {
   5:         AccountAge = accountAge;
   6:         CreditScore = creditScore;
   7:         AccountInterest = accountInterest;
   8:     }
   9:  
  10:     public int AccountAge { get; private set; }
  11:     public int CreditScore { get; private set; }
  12:     public AccountInterest AccountInterest { get; private set; }
  13:  
  14:     public double CalculateInterestRate()
  15:     {
  16:         if (CreditScore > 800)
  17:             return 0.02;
  18:  
  19:         if (AccountAge > 10)
  20:             return 0.03;
  21:  
  22:         return 0.05;
  23:     }
  24: }
  25:  
  26: public class AccountInterest
  27: {
  28:     public BankAccount Account { get; private set; }
  29:  
  30:     public AccountInterest(BankAccount account)
  31:     {
  32:         Account = account;
  33:     }
  34:  
  35:     public double InterestRate
  36:     {
  37:         get { return Account.CalculateInterestRate(); }
  38:     }
  39:  
  40:     public bool IntroductoryRate
  41:     {
  42:         get { return Account.CalculateInterestRate() < 0.05; }
  43:     }
  44: }
```

The point of interest here is the BankAccount.CalculateInterest method. A hint that you need the Move Method refactoring is when another class is using a method more often then the class in which it lives. If this is the case it makes sense to move the method to the class where it is primarily used. This doesn’t work in every instance because of dependencies, but it is overlooked often as a worthwhile change.

In the end you would end up with something like this:
```Java

   1: public class BankAccount
   2: {
   3:     public BankAccount(int accountAge, int creditScore, AccountInterest accountInterest)
   4:     {
   5:         AccountAge = accountAge;
   6:         CreditScore = creditScore;
   7:         AccountInterest = accountInterest;
   8:     }
   9:  
  10:     public int AccountAge { get; private set; }
  11:     public int CreditScore { get; private set; }
  12:     public AccountInterest AccountInterest { get; private set; }
  13: }
  14:  
  15: public class AccountInterest
  16: {
  17:     public BankAccount Account { get; private set; }
  18:  
  19:     public AccountInterest(BankAccount account)
  20:     {
  21:         Account = account;
  22:     }
  23:  
  24:     public double InterestRate
  25:     {
  26:         get { return CalculateInterestRate(); }
  27:     }
  28:  
  29:     public bool IntroductoryRate
  30:     {
  31:         get { return CalculateInterestRate() < 0.05; }
  32:     }
  33:  
  34:     public double CalculateInterestRate()
  35:     {
  36:         if (Account.CreditScore > 800)
  37:             return 0.02;
  38:  
  39:         if (Account.AccountAge > 10)
  40:             return 0.03;
  41:  
  42:         return 0.05;
  43:     }
  44: }
 ```
Simple enough!