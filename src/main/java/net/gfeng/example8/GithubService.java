package net.gfeng.example8;

import org.eclipse.egit.github.core.CommitUser;
import org.eclipse.egit.github.core.RepositoryCommit;


public class GithubService {

	public void doSomething(RepositoryCommit commit) {
		
		CommitUser user = commit.getCommit().getCommitter();
		
		System.out.println("Hey you received new commit");
		System.out.println("Name: "+user.getName()+" Email: "+user.getEmail());
		
	}
	
}
