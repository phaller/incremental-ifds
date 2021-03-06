package soot.jimple.interproc.ifds;

/**
 * A wrapper that can be used to profile flow functions.
 */
public class ProfiledFlowFunctions<N, D, M> implements FlowFunctions<N, D, M> {

	protected final FlowFunctions<N, D, M> delegate;
	
	public long durationNormal, durationCall, durationReturn, durationCallReturn;

	public ProfiledFlowFunctions(FlowFunctions<N, D, M> delegate) {
		this.delegate = delegate;
	}

	public FlowFunction<D> getNormalFlowFunction(N curr, N succ) {
		long before = System.currentTimeMillis();
		FlowFunction<D> ret = delegate.getNormalFlowFunction(curr, succ);
		long duration = System.currentTimeMillis() - before;
		durationNormal += duration;
		return ret;
	}

	public FlowFunction<D> getCallFlowFunction(N callStmt, M destinationMethod) {
		long before = System.currentTimeMillis();
		FlowFunction<D> res = delegate.getCallFlowFunction(callStmt, destinationMethod);
		long duration = System.currentTimeMillis() - before;
		durationCall += duration;
		return res;
	}

	public FlowFunction<D> getReturnFlowFunction(N callSite, M calleeMethod, N exitStmt, N returnSite) {
		long before = System.currentTimeMillis();
		FlowFunction<D> res = delegate.getReturnFlowFunction(callSite, calleeMethod, exitStmt, returnSite);
		long duration = System.currentTimeMillis() - before;
		durationReturn += duration;
		return res;
	}

	public FlowFunction<D> getCallToReturnFlowFunction(N callSite, N returnSite) {
		long before = System.currentTimeMillis();
		FlowFunction<D> res = delegate.getCallToReturnFlowFunction(callSite, returnSite);
		long duration = System.currentTimeMillis() - before;
		durationCallReturn += duration;
		return res;
	}
	
}
