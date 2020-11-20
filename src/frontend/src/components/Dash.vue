<template>
    <div class="p-grid p-fluid dashboard">
        <div class="p-grid p-col-12 p-d-flex">
            <div class="p-col-12 p-lg-4">
                <div class="card summary">
                    <span class="title">일간 코인</span>
                    <span class="detail">금일 누적 코인</span>
                    <span class="count visitors">12</span>
                </div>
            </div>
            <div class="p-col-12 p-lg-4">
                <div class="card summary">
                    <span class="title">주간 코인</span>
                    <span class="detail">주간 누적 코인</span>
                    <span class="count purchases">333</span>
                </div>
            </div>
            <div class="p-col-12 p-lg-4">
                <div class="card summary">
                    <span class="title">목표 달성률</span>
                    <span class="detail">해당 목표 달성률</span>
                    <span class="count revenue">100%</span>
                </div>
            </div>
        </div>
        <div class="p-col-12 p-grid p-d-flex">
            <div class="p-col-12 p-lg-6">
                <div class="card">
                    <h1 style="font-size:16px">Recent Data</h1>
                    <!-- DataTable -->
                    <DataTable :value="donations" class="p-datatable-customers" :rows="10" style="margin-bottom: 20px;" :paginator="true">
                        <!-- <Column field="user_name" header="UserID" :sortable="true"></Column> -->
                        <Column field="user_email" header="UserEmail" :sortable="true"></Column>
                        <Column field="donation_price" header="Price" :sortable="true"></Column>
                        <Column field="donation_place" header="Place" :sortable="true"></Column>
                        <Column field="donation_date" header="Date" :sortable="true"></Column>
                    </DataTable>
                </div>
            </div>
            <div class="p-col-12 p-lg-6">
                <div class="card">
                    <Chart type="line" :data="lineData" />
                </div>
                <div class="card">
                    <Chart type="bar" :data="lineData" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import DonationService from '../service/DonationService';

export default {
    data() {
        return {
            lineData: {
				labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
				datasets: [
					{
						label: 'First Dataset',
						data: [65, 59, 80, 81, 56, 55, 40],
						fill: false,
						backgroundColor: '#2f4860',
						borderColor: '#2f4860'
					},
					{
						label: 'Second Dataset',
						data: [28, 48, 40, 19, 86, 27, 90],
						fill: false,
						backgroundColor: '#00bb7e',
						borderColor: '#00bb7e'
					}
				]
			},
            donations: null,
        }
    },
    donationService: null,
    created() {
        this.donationService = new DonationService();
    },
    mounted() {
        this.donationService.getDonations().then(data => this.donations = data);
    }
}
</script>

<style>

</style>