When breaking apart responsibilities on a class this is enforcing Single Responsiblity Principle from SOLID.
It’s an easy approach to apply this refactoring although it’s often disputed as what consitutes a
“responsibility”. While I won’t be answering that here, I will show a clear cut example of a class that can be
broken into several classes with specific responsibilities.
 
As you can see here, the Video class has two responsibilities, once for handling video rentals, and another
for managing how many rentals a customer has. We can break out the customer logic into it’s own class to
help seperate the responsibilities.
 
 
 #### original C# code from the book:
 ```cs
 1: public class Video
 2: {
 3: public void PayFee(decimal fee)
 4: {
 5: }
 6:
 7: public void RentVideo(Video video, Customer customer)
 8: {
 9: customer.Videos.Add(video);
 10: }
 11:
 12: public decimal CalculateBalance(Customer customer)
 13: {
 14: return customer.LateFees.Sum();
 15: }
 16: }
 17:
 18: public class Customer
 19: {
 20: public IList<decimal> LateFees { get; set; }
 21: public IList<Video> Videos { get; set; }
 22: }
 
 1: public class Video
 2: {
 3: public void RentVideo(Video video, Customer customer)
 4: {
 5: customer.Videos.Add(video);
 6: }
 7: }
 8:
 9: public class Customer
 10: {
 11: public IList<decimal> LateFees { get; set; }
 12: public IList<Video> Videos { get; set; }
 13:
 14: public void PayFee(decimal fee)
 15: {
 16: }
 17:
 18: public decimal CalculateBalance(Customer customer)
 19: {
 20: return customer.LateFees.Sum();
 21: }
 22: } 
 
 ```