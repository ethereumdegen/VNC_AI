package com.glavsoft.ai.ideas;

public class SceneObject {
	
	/*
	 * 
	 * When a new image enters the feed, it is assumed to be the next image frame in sequential order.  Some of the color blobs
	 * (previously assigned to SceneObjects) may have moved slightly.  Each new blob will be examined to see if it matches
	 * closely enough (translated? color change?) with an existing SceneObject based on the SceneObject's old blobs for comparison.
	 * 
	 * If no older blobs match the new blob at all, the new blob must be a brand new object to the scene. 
	 *  In this case, a new SceneObject will be created for it.
	 * 
	 * If this process works, the AI will be able to better understand the unique objects that it is looking at. It can also
	 * easily understand how they are changing or moving over time.
	 *  
	 */
	
	long uniqueID;
	
	ColorBlob[] associatedColorBlobs;
	
	
	
	
	
	
}
