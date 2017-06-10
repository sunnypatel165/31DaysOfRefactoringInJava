This is probably one of the most used refactoring in the forms of methods that are used in more than one
place. Duplication will quickly sneak up on you if you’re not careful and give in to apathy. It is often added to the codebase through laziness or a developer that is trying to produce as much code as possible, as
quickly as possible. I don’t think we need anymore description so let’s look at the code.

```Java
public class MedicalRecord {
	private LocalDateTime dateArchived;
	
	private boolean isArchived;
	
	public void archiveRecord(){
		this.dateArchived = LocalDateTime.now();
		this.isArchived = true;
	}
	public void closeRecord(){
		this.dateArchived = LocalDateTime.now();
		this.isArchived = true;
	}
	
	public LocalDateTime getDateArchived() {
		return dateArchived;
	}
	public void setDateArchived(LocalDateTime dateArchived) {
		this.dateArchived = dateArchived;
	}
	public boolean isArchived() {
		return isArchived;
	}
	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}
}
```
We move the duplicated code to a shared method and voila! No more duplication. Please enforce this
refactoring whenever possible. It leads to much fewer bugs because you aren’t copy/pasting the bugs
throughout the code.

```Java
public class MedicalRecord {
	private LocalDateTime dateArchived;

	private boolean isArchived;

	public void archiveRecord() {
		switchToArchive();
	}

	public void closeRecord() {
		switchToArchive();
	}

	public void switchToArchive() {
		this.dateArchived = LocalDateTime.now();
		this.isArchived = true;
	}

	public LocalDateTime getDateArchived() {
		return dateArchived;
	}

	public void setDateArchived(LocalDateTime dateArchived) {
		this.dateArchived = dateArchived;
	}

	public boolean isArchived() {
		return isArchived;
	}

	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}
}

```
 
 
#### Original C# code from the book
```cs
 1: public class MedicalRecord
 2: {
 3: public DateTime DateArchived { get; private set; }
 4: public bool Archived { get; private set; }
 5:
 6: public void ArchiveRecord()
 7: {
 8: Archived = true;
 9: DateArchived = DateTime.Now;
 10: }
 11:
 12: public void CloseRecord()
 13: {
 14: Archived = true;
 15: DateArchived = DateTime.Now;
 16: }
 17: }
 
 1: public class MedicalRecord
 2: {
 3: public DateTime DateArchived { get; private set; }
 4: public bool Archived { get; private set; }
 5:
 6: public void ArchiveRecord()
 7: {
 8: SwitchToArchived();
 9: }
 10:
 11: public void CloseRecord()
 12: {
 13: SwitchToArchived();
 14: }
 15:
 16: private void SwitchToArchived()
 17: {
 18: Archived = true;
 19: DateArchived = DateTime.Now;
 20: }
 21: } 
```