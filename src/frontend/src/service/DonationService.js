import axios from 'axios';

export default class DonationService {

	getDonations() {
		return axios.get('assets/layout/data/donations.json').then(res => res.data.data);
    }
}
