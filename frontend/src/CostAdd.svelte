<script>

</script>

<div class="cost-add">
    <div class="text">记录一笔消费</div>
    <div class="form">
        <input type="text" placeholder="价格" autocomplete="off">
        <input type="text" placeholder="备注" autocomplete="off">
        <div class="bottom">
            <button type="submit">保存</button>
        </div>
    </div>
</div>

<style>
.cost-add {
    margin: 0 auto;
    margin-top: 100px;
    width: 337px;
}

.text {
    font-size: 20px;
    margin-bottom: 20px;
}

.form {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100%;
}

input {
    width: 100%;
}

.form .bottom {
    display: flex;
    flex-direction: row;
    width: 100%;
    margin-top: 20px;
}
</style>