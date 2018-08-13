package com.nisum.demotest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.stereotype.Service;

import com.nisum.demotest.data.ZipCodeRange;
import com.nisum.demotest.data.ZipCodeRangeRequest;
import com.nisum.demotest.data.ZipCodeRangeResponse;

@Service
public class ZipCodeRangeService {

	public void getZipCodeRangeList(ZipCodeRangeRequest zipCodeRangeRequest,
			ZipCodeRangeResponse zipCodeRangeResponse) {

		List<ZipCodeRange> zipCodeRangeList = new ArrayList<ZipCodeRange>();

		zipCodeRangeRequest.getRangeList().forEach(range -> {
			addRange(range, zipCodeRangeList);
		});

		zipCodeRangeResponse.setRangeList(zipCodeRangeList);

	}

	public static void addRange(ZipCodeRange requestedZipCodeRange, List<ZipCodeRange> rangeList) {
		if (rangeList.isEmpty())
			rangeList.add(requestedZipCodeRange);
		else {
			ListIterator<ZipCodeRange> rangeListIterator = rangeList.listIterator();
			boolean isEligible = true;
			while (rangeListIterator.hasNext()) {
				ZipCodeRange zipCodeRange = rangeListIterator.next();
				if (isNewRangeExistsWithinExistingRange(requestedZipCodeRange, zipCodeRange)) {
					isEligible = false;
				}
				else if (isNewRangeDoesNotExistsWithinExistingRange(requestedZipCodeRange, zipCodeRange)) {
					isEligible = true;
				}
				else {
					if (zipCodeRange.getStartRange() < requestedZipCodeRange.getStartRange()) {
						requestedZipCodeRange.setStartRange(zipCodeRange.getStartRange());
					}
					if (zipCodeRange.getEndRange() > requestedZipCodeRange.getEndRange()) {
						requestedZipCodeRange.setEndRange(zipCodeRange.getEndRange());
					}
					rangeListIterator.remove();
					isEligible = true;
				}
			}
			if (isEligible)
				rangeList.add(requestedZipCodeRange);
		}
	}

	private static boolean isNewRangeDoesNotExistsWithinExistingRange(ZipCodeRange requestedRange, ZipCodeRange range) {
		return range.getStartRange() > requestedRange.getEndRange()
				|| range.getEndRange() < requestedRange.getStartRange();
	}

	private static boolean isNewRangeExistsWithinExistingRange(ZipCodeRange requestedRange, ZipCodeRange range) {
		return range.getStartRange() <= requestedRange.getStartRange()
				&& range.getEndRange() >= requestedRange.getEndRange();
	}

}
