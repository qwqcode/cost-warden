<script>

</script>

<div class="act-bar">
    <div class="item active">记录</div>
    <div class="item">分析</div>
    <div class="item">设置</div>
</div>

<style>
.act-bar {
    margin-top: 10px;
    margin-bottom: 0;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 50px;
}

.item {
    font-size: 20px;
    position: relative;
    cursor: pointer;
}

.item:not(:last-child) {
    margin-right: 2.4em;
}

.item:hover {
    color: #00A3FF;
}

.item.active::before {
    position: absolute;
    content: '';
    display: block;
    width: 11px;
    height: 11px;
    left: -20px;
    top: 50%;
    transform: translateY(-50%);
    background: #00A3FF;
}
</style>