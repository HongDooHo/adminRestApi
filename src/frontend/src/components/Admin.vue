<template>
    <div class="floatlabel-demo">
        <div class="card">
            <h5>기부처 별 코인 입력</h5>
            <div class="p-grid p-formgrid p-fluid p-mt-5">
                <div class="p-grid p-field p-col-12">
                    <label for="donation" class="p-col-12 p-mb-2 p-md-2 p-mb-md-0">기부처</label>
                    <div class="p-col-12 p-md-10">
                        <Dropdown 
                            id="donation"
                            v-model="form.place" 
                            :options="dropdownCities" 
                            optionLabel="name" 
                            placeholder="기부처를 선택해주세요."/>
                    </div>
                </div>
                <div class="p-grid p-field p-col-12">
                    <label for="userId" class="p-col-12 p-mb-2 p-md-2 p-mb-md-0">ID</label>
                    <div class="p-col-12 p-md-10">
                        <InputText id="userId" type="text" v-model="form.userId" placeholder="기부자의 ID를 입력해주세요." />
                    </div>
                </div>
                <div class="p-grid p-field p-col-12">
                    <label for="donaCoin" class="p-col-12 p-mb-2 p-md-2 p-mb-md-0">기부코인</label>
                    <div class="p-col-12 p-md-10">
                        <InputText id="donaCoin" type="text" v-model="form.coinValue" placeholder="기부 금액을 입력해주세요." />
                    </div>
                </div>
                <div class="p-d-flex p-col-4 p-offset-4">
                    <Button label="SUBMIT" class="p-mr-3" v-on:click="onSubmit"></Button>
                    <Button label="RESET" class="p-button-danger" v-on:click="onReset"></Button>
                </div>
            </div>
        </div>
        
        <!-- <div class="p-col-12 p-fluid">
            <div class="card">
                <Card class="p-shadow-4">
                    <template #content>
                        <h2>일별 코인 기부 통계</h2>
                        <Chart type="bar" :data="barData" />
                    </template>
                </Card>
            </div>
        </div> -->
    </div>
</template>

<script>
export default {
    data() {
        return {
            form: {
                userId: '',
                coinValue: '',
                place: null,
            },
            dropdownCities: [
				{name: 'New York', code: 'NY'},
				{name: 'Rome', code: 'RM'},
				{name: 'London', code: 'LDN'},
				{name: 'Istanbul', code: 'IST'},
				{name: 'Paris', code: 'PRS'}
			],
            dropdownCity: null,
            barData: {
				labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
				datasets: [
					{
						label: 'My First dataset',
						backgroundColor: '#2f4860',
						data: [65, 59, 80, 81, 56, 55, 40]
					},
					{
						label: 'My Second dataset',
						backgroundColor: '#00bb7e',
						data: [28, 48, 40, 19, 86, 27, 90]
					}
				]
			},
        }
    },
    methods: {
        onSubmit(event) {
            event.preventDefault();
            alert(JSON.stringify(this.form));
        },
        onReset(event) {
            event.preventDefault();
            this.form.userId = '';
            this.form.coinValue = '';
            this.form.place = null;
        }
    }
}
</script>

<style>

</style>