import * as Api from './api'
import { workspace, tags, selectedTid, costs, editCost } from './stores'

export function SwitchWorkSpace(name) {
    if (name === 'add') editCost.set({});

    workspace.set(name)
}

export function FetchTags() {
    Api.getTags().then(rTags => {
        tags.set(rTags)
        selectedTid.set(rTags[0].tid)
    })
}

export function FetchCosts(filter) {
    Api.getCosts(filter).then((costs) => {
        updateCosts(costs)
    })
}

function updateCosts(src) {
    ;(()=>{})(costs); // TODO: XD <-- add this solve a rollup compile bug
    costs.set([]); // clear all original items
    const distArr = []
    src.forEach((c) => {
        // @sample "2022-05-30 16:02:21.322000000"
        const time = c.date.split(' ')[1].split('.')[0].substr(0, 5)
        const date = c.date.split(' ')[0].replace(/-/g, '/');
        const day = date.split('/')[2];
        const month = date.split('/')[1];
        const year = date.split('/')[0];
        c = { time, date, day, month, year, ...c }
        let dayArr = distArr.find(o => (o.date === date));
        if (!dayArr) {
            dayArr = { date, day, month, year, costs: [] }
            distArr.push(dayArr)
        }
        dayArr.costs.push(c);
    })
    costs.set(distArr) // notify arr changed
}

export function EditCost(costObj) {
    editCost.set(costObj)
}