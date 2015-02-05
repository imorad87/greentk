package model.calibration.certificates.index;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Balance")
public class BIndex extends TSCertificateIndex {

}
