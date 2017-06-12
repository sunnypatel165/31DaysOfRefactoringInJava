This is probably one of the most used refactoring in the forms of methods that are used in more than one
place. Duplication will quickly sneak up on you if you’re not careful and give in to apathy. It is often added to the codebase through laziness or a developer that is trying to produce as much code as possible, as
quickly as possible. I don’t think we need any more description, so let’s look at the code.

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
public class MedicalRecord
{
	public DateTime DateArchived { get; private set; }
	public bool Archived { get; private set; }

	public void ArchiveRecord()
	{
		Archived = true;
		DateArchived = DateTime.Now;
	}

	public void CloseRecord()
	{
		Archived = true;
		DateArchived = DateTime.Now;
	}
}

public class MedicalRecord
{
	public DateTime DateArchived { get; private set; }
	public bool Archived { get; private set; }

	public void ArchiveRecord()
	{
		SwitchToArchived();
	}

	public void CloseRecord()
	{
		SwitchToArchived();
	}

	private void SwitchToArchived()
	{
		Archived = true;
		DateArchived = DateTime.Now;
	}
}
```
