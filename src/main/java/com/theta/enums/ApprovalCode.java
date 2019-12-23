package com.theta.enums;

public enum ApprovalCode{
	// Do Nothing 
	HOLD,
	// Do Nothing - BUT respect the limits - enter and exit
	HOLD_LMT,
	// Approve the recommendation
	APPROVE,
	// Enter only - kill the position - do not execute
	KILL,
	// Exit only - close the position immediately
	CLOSE;
}