package view.newCertificatesWizard;


public interface CalibrationElementListener {
	
	void acceptanceLimitUpdated(double acceptanceLimit);
	
	void maxRangeUpdated(double maxRange);
	
	void calculationMethodUpdated(int method);

}
